package com.wyj.ds_algorithm.smartUtil.ratelimiter;

/**
 * 漏桶算法
 * 有一个桶，比如容量10，流量就是水，往桶里浇。
 * 桶以一定的速率漏水，桶满了，就不接受请求了。
 *
 * 思路：在请求进来的时候 再去计算 是否有足量的资源使用。
 * 用water 保存进来的流量，当新的请求进来的时候，根据water 和上一次的请求时间，计算上一次请求时间 到现在 应该流失多少请求，重新计算water。
 * 如果water没有达到桶上限，就可以通过，达到了就不可以通过
 *
 * 优化：synchronized 锁的有点重，所有进来的请求一股脑全部排队。可以控制单位时间perTime内的不加锁，直接water++，如果跨时间了，使用cas 更新ctl 成员变量，更新成功的才可刷新water，更新不成功的就cas继续。
 */
public class LeakBucketLimiter implements RateLimiter{

    private final int bucketCnt;

    private final int perCnt;

    private final int perTime;

    private long water;

    private long startMillis;

    public LeakBucketLimiter(int bucketCnt, int perCnt, int perTime) {
        this.bucketCnt = bucketCnt;
        this.perCnt = perCnt;
        this.perTime = perTime;
        water = 0;
        startMillis = System.currentTimeMillis();
    }


    @Override
    public synchronized boolean tryAcquire() {
        if (water == 0) {
            water = 1;
            return true;
        }

        long currentMillis = System.currentTimeMillis();
        water = Math.max(0, water - (currentMillis - startMillis) / perTime * perCnt);
        startMillis = currentMillis;
        if (water < bucketCnt) {
            water ++;
            return true;
        } else {
            return false;
        }
    }
}
