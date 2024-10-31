package com.wyj.ds_algorithm.smartUtil.ratelimiter;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 令牌桶。令牌以一定的速率往桶中添加，每一个请求过来，有令牌就可以过。
 *
 * 优化：单位perTime时间内过来的请求 将不再 加锁。单位时间外的请求进来，使用cas 更新tokenCnt，
 */
public class TokenBucketLimiter2 implements RateLimiter{

    private final int bucketCapacity;

    private final int perTime;

    private final int perCnt;

    private AtomicLong tokenCnt;

    private long startMillis;

    public TokenBucketLimiter2(int bucketCapacity, int perTime, int perCnt) {
        this.bucketCapacity = bucketCapacity;
        this.perTime = perTime;
        this.perCnt = perCnt;

        tokenCnt = new AtomicLong(0);
        startMillis = System.currentTimeMillis();
    }

    @Override
    public boolean tryAcquire() {
        long currentMillis = System.currentTimeMillis();
        if (currentMillis - startMillis < perTime) {
            // tokenCnt 可能小于0
            if (tokenCnt.decrementAndGet() > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            // 需要重新计算tokenCnt
            synchronized (this) {
                long curCnt = tokenCnt.get();
                if (curCnt < 0) {
                    System.out.println("阻塞的数量:" + curCnt);
                    curCnt = 0;
                }

                curCnt = Math.min(bucketCapacity, curCnt + (currentMillis - startMillis) / perTime * perCnt);
                tokenCnt.set(curCnt);
                startMillis = currentMillis;
            }
            if (tokenCnt.decrementAndGet() > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    static String s2 = "10";

    static void func(String a) {
        a = "3";
    }

    public static void main(String[] args) {


        String a = "2";
        func(a);
        System.out.println(a);

        String b = new String("2");
        func(b);
        System.out.println(b);

        func(s2);
        System.out.println(s2);
    }
}
