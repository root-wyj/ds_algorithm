package com.wyj.ds_algorithm.smartUtil.ratelimiter;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * 固定窗口限流器，计数器
 * 在1s内 通过 N个请求
 * 缺点：临界问题，请求达到2N
 */
public class SimpleRateLimiter implements RateLimiter{

    /**
     * 单位时间，ms
     */
    private final int perTimeUnit;

    /**
     * 单位时间允许通过的数量
     */
    private final int perCnt;

    /**
     * 当前单位时间 通过的数量
     */
    private AtomicInteger cnt;

    /**
     * 开始时间
     */
    private long startTimeMillis;

    public SimpleRateLimiter(int perTimeUnit, int perCnt) {
        this.perTimeUnit = perTimeUnit;
        this.perCnt = perCnt;
        startTimeMillis = System.currentTimeMillis();
        cnt = new AtomicInteger(0);
    }

    public boolean tryAcquire() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - startTimeMillis < perTimeUnit) {
            int curCnt = cnt.incrementAndGet();
            if (curCnt > perCnt) {
                return false;
            } else {
                return true;
            }
        } else {
            synchronized (this) {
                // 双重校验，防止重新初始化
                if (currentTimeMillis - startTimeMillis > perTimeUnit) {
                    startTimeMillis = currentTimeMillis;
                    cnt.set(0);
                }
            }
            int curCnt = cnt.incrementAndGet();
            if (curCnt > perCnt) {
                return false;
            } else {
                return true;
            }
        }
    }
}
