package com.wyj.ds_algorithm.leecode;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author wuyingjie <13693653307@163.com>
 * Created on 2021-05-10
 */
public class AliSubscribeGoods {

    private ConcurrentHashMap<Long, ConcurrentHashMap<Long, Long>> goodsSubscribeMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Long, AtomicLong> goodsSubscribeCountMap = new ConcurrentHashMap<>();
    private final Object goodsSubscribeMapLock = new Object();
    private final Object goodsSubscribeCountMapLock = new Object();

    public boolean subscribe(Long goodId, Long userId) {
        ConcurrentHashMap<Long, Long> userSubscribeMap = goodsSubscribeMap.get(goodId);
        if (userSubscribeMap == null) {
            synchronized (goodsSubscribeMapLock) {
                userSubscribeMap = goodsSubscribeMap.get(goodId);
                if (userSubscribeMap == null) {
                    userSubscribeMap = new ConcurrentHashMap<>();
                    goodsSubscribeMap.put(goodId, userSubscribeMap);
                }
            }
        }

        Long put = userSubscribeMap.putIfAbsent(userId, userId);
        if (put != null) {
            // 说明之前订阅过
            return true;
        }

        AtomicLong subscribeCount = goodsSubscribeCountMap.get(goodId);
        if (subscribeCount == null) {
            synchronized (goodsSubscribeCountMapLock) {
                subscribeCount = goodsSubscribeCountMap.get(goodId);
                if (goodsSubscribeCountMap == null) {
                    subscribeCount = new AtomicLong(1);
                    goodsSubscribeCountMap.put(goodId, subscribeCount);
                }
            }
        } else {
            subscribeCount.addAndGet(1);
        }

        return true;
    }

    public long sumSubscribe() {
        long sum = 0;
        for (AtomicLong item : goodsSubscribeCountMap.values()) {
            sum += item.get();
        }
        return sum;
    }

    public long sumSubscribe(Long goodId) {
        return Optional.ofNullable(goodsSubscribeCountMap.get(goodId)).map(AtomicLong::get).orElse(0L);
    }



}
