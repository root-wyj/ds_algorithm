package com.wyj.ds_algorithm.smartUtil.ratelimiter;

/**
 * 令牌桶。令牌以一定的速率往桶中添加，每一个请求过来，有令牌就可以过。
 */
public class TokenBucketLimiter implements RateLimiter{

    /**
     * 桶大小
     */
    private final int bucketCapacity;

    /**
     * 单位时间
     */
    private final int perTime;

    /**
     * 单位时间增加的令牌
     */
    private final int perCnt;

    /**
     * 当前还剩的令牌
     */
    private long tokenCnt;

    /**
     * 上一次请求的时间，开始统计的时间
     */
    private long startMillis;

    public TokenBucketLimiter(int bucketCapacity, int perTime, int perCnt) {
        this.bucketCapacity = bucketCapacity;
        this.perTime = perTime;
        this.perCnt = perCnt;

        tokenCnt = bucketCapacity;
        startMillis = System.currentTimeMillis();
    }

    @Override
    public synchronized boolean tryAcquire() {
        long currentMillis = System.currentTimeMillis();
        tokenCnt = Math.min(bucketCapacity, tokenCnt + (currentMillis - startMillis) / perTime * perCnt);
        startMillis = currentMillis;
        if (tokenCnt > 0) {
            tokenCnt --;
            return true;
        } else {
            return false;
        }
    }
}
