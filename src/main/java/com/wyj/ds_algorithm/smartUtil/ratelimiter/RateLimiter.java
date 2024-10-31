package com.wyj.ds_algorithm.smartUtil.ratelimiter;

/**
 * 限流器
 * 参考子：https://developer.aliyun.com/article/1097563
 *
 */
public interface RateLimiter {

    /**
     * 尝试获取资源
     * @return true 可以继续，false 没有获取到
     */
    boolean tryAcquire();

}
