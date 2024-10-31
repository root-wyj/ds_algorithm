package com.wyj.ds_algorithm.smartUtil.ratelimiter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class RateLimiterTest {


    public static void main(String[] args) throws InterruptedException {
//        RateLimiter rateLimiter = new SimpleRateLimiter(1000, 10);
//        RateLimiter rateLimiter = new LeakBucketLimiter(20, 3, 100);
//        RateLimiter rateLimiter = new TokenBucketLimiter(50, 100, 3);
//        RateLimiter rateLimiter = new TokenBucketLimiter2(50, 100, 3);
        RateLimiter rateLimiter = new SlidingWindowLimiter(1, 100);

        ExecutorService executor = Executors.newFixedThreadPool(20);
        final int threadNum = 5, turns = 50;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        AtomicLong successCnt = new AtomicLong(0);
        AtomicLong failCnt = new AtomicLong(0);
        long start = System.currentTimeMillis();
        for (int j=0; j<threadNum; j++) {
            executor.execute(() -> {
                try {
                    for (int i = 0; i < turns; i++) {
                        if (rateLimiter.tryAcquire()) {
                            successCnt.incrementAndGet();
                        } else {
                            failCnt.incrementAndGet();
                            Thread.sleep(200);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                    System.out.println(Thread.currentThread().getName() + " end");
                }
            });
        }

        countDownLatch.await();
        System.out.println("success:" + successCnt.get() + ", fail:" + failCnt.get() + ", time:" + (System.currentTimeMillis() - start) / 1000);
        executor.shutdown();
    }
}
