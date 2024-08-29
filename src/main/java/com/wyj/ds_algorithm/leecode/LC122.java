package com.wyj.ds_algorithm.leecode;

/**给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。

 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。

 返回 你能获得的 最大 利润 。
 * @author wuyingjie
 * Date: 2024/8/17
 * @see LC121 一次买卖
 * @see LC122 不限次数买卖
 */
public class LC122 {


    public static int maxProfit(int[] prices) {

        int total = 0;
        int index = 0;

        while (index < prices.length) {
            // 下坡路 find buy
            int buyIndex = index;
            while (buyIndex < (prices.length - 1) && prices[buyIndex+1] <= prices[buyIndex]) {
                buyIndex++;
            }
            if (buyIndex >= prices.length - 1) {
                //说明没找到
                break;
            }

            // 上坡路 find sell
            int sellIndex=buyIndex+1;
            while (sellIndex+1 < prices.length && prices[sellIndex+1] >= prices[sellIndex]) {
                sellIndex++;
            }
            total += prices[sellIndex] - prices[buyIndex];

            index = sellIndex + 1;
        }

        return total;

    }


    public static void main(String[] args) {
//        System.out.println(maxProfit(new int[] {7,1,5,3,6,4}));
        System.out.println(maxProfit(new int[] {3,2,6,5,0,3}));
    }


}
