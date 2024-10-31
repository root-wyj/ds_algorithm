package com.wyj.ds_algorithm.smartUtil.ratelimiter;

/**
 * 滑动窗口
 */
public class SlidingWindowLimiter implements RateLimiter{

    private static class Node {
        long timeUnit;
        long cnt;

        Node(long timeUnit, long cnt) {
            this.timeUnit = timeUnit;
            this.cnt = cnt;
        }
    }

    private static final int PER_TIME = 100;

    private Node[] window;
    private int startIndex;

    /**
     * 某时间窗口 允许通过的流量
     */
    private final long capacity;

    /**
     * 单位ms，时间窗口的跨度
     */
    private final int timeSpan;

    public SlidingWindowLimiter(int timeSpanInSecond, long capacity) {
        this.timeSpan = timeSpanInSecond * 1000;
        this.capacity = capacity;
        window = new Node[timeSpan / PER_TIME];
        startIndex = 0;
    }

    @Override
    public synchronized boolean tryAcquire() {
        long curTimeUnit = System.currentTimeMillis() / PER_TIME;
        if (!calculate(curTimeUnit)) {
            return false;
        }

        if (window[startIndex] != null && window[startIndex].timeUnit == curTimeUnit) {
            window[startIndex].cnt++;
        } else {
            Node node = new Node(curTimeUnit, 1);
            startIndex = getNextIndex(startIndex);
            window[startIndex] = node;
        }
        return true;
    }

    public boolean calculate(long curTimeUnit) {
        long endTimeUnit = curTimeUnit - timeSpan / PER_TIME;
        long sum = 0;
        int index = startIndex;
        while (window[index] != null && window[index].timeUnit > endTimeUnit) {
            sum += window[index].cnt;
            index = getPreIndex(index);
        }
        return sum < capacity;
    }

    private int getPreIndex(int index) {
        return (index - 1 + window.length) % window.length;
    }

    private int getNextIndex(int index) {
        return (index + 1) % window.length;
    }
}
