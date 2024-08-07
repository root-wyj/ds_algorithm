package com.wyj.ds_algorithm.dp.beibao;

/**
 * 背包问题
 * @author wuyingjie
 * Date: 2024/8/1
 */
public class DP_BEIBAO {

    /* 0-1背包问题
    有 n 件物品和有一个最多能装重量为 W 的背包。第 i 件物品的重量为 weight[i]，价值为 value[i]，每件物品有且只有 1 件。请问在总重量不超过背包载重上限的情况下，能装入背包的最大价值是多少？
     */

    public static int zeroOnePack(int packWeight, int[] weight, int[] value, int lastGoodId) {

        if (lastGoodId == 0) {
            if (packWeight > weight[lastGoodId]) {
                return value[lastGoodId];
            } else {
                return 0;
            }
        }

        if (packWeight > weight[lastGoodId]) {
            int notContains = zeroOnePack(packWeight, weight, value, lastGoodId - 1);
            int contains = zeroOnePack(packWeight-weight[lastGoodId], weight, value, lastGoodId - 1) + value[lastGoodId];
            return Math.max(notContains, contains);
        } else {
            return zeroOnePack(packWeight, weight, value, lastGoodId - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(zeroOnePack(400, new int[]{100, 120, 150, 200, 50}, new int[]{500, 79, 69, 39, 10}, 4));
        System.out.println(zeroOnePack(400, new int[]{50, 200, 150, 120, 100}, new int[]{10, 39, 69, 79, 500}, 4));
    }

}
