package com.wyj.ds_algorithm.leecode;

/**
 * 买卖股票的最佳时机
 * 限制N次买卖的 最大值
 * @author wuyingjie
 * Date: 2024/8/17
 * @see LC121 一次买卖
 * @see LC122 不限次数买卖
 */
public class LC188 {


    /*
    思路：比如说 买卖2ci的最大值 LC123
    用prices=[7,1,5,3,6,4] 表示每天的价格
    用b1=[-7,-1,-1,-1,-1,-1] 表示买入一手每天的最大利润 b1 = max(b1', -price[i])，理解为 买入一手 今天的最大利润=max(昨天买入的最大利润, 今天买)
    用s1=[0,0,4,4,5,5] 表示卖出一手每天的最大利润s1=max(s1', b1+price[i])，理解为 卖出一手 今天的最大利润=max(昨天卖出的最大利润, 今天买入的最大利润+今天卖)
    tips: s[0]=0, 理解为，第一天买卖，或者第一天不交易。
    那么只买卖一轮的最大值 就是s1[price.length-1] = 5

    用b2=[-7,-1,-1,1,1,1] 表示第二次买入一手每天的最大利润 b2=max(b2', s1[i]-price[i])，理解为 买入第二手 今天的最大利润=max(昨天第二手买入的最大利润，今天第一手卖出的最大利润-今天的价格)
    用s2=[0,0,4,4,7,7] 表示第二次卖出一手每天的最大利润 s2=max(s2', b2[i] + price[i]) 理解为 卖出第二手 今天的最大利润=max(昨天第二手卖出的最大利润， 今天第二手买入的最大利润+今天的价格)

    同样的bn 和 sn 也可以推算出来

    可以吧第1手买入时，依赖的上一手卖出的最大利润标记为全是0的数组
     */

    public static int maxProfit(int k, int[] prices) {
        // 第一手买卖钱的 收益全是0
        int[] lastProfit = new int[prices.length];
        for (int i=0; i<k; i++) {
            lastProfit = maxNextProfit(prices, lastProfit);
        }
        return lastProfit[prices.length-1];
    }

    public static int[] maxNextProfit(int[] prices, int[] lastProfit) {
//            // 表示第一手
//            int[] b1 = new int[prices.length];
//            int[] s1 = new int[prices.length];
//            b1[0] = -prices[0];
//            s1[0] = 0;
//            for (int i=1; i<prices.length; i++) {
//                b1[i] = Math.max(b1[i-1], -prices[i]);
//                s1[i] = Math.max(s1[i-1], b1[i] + prices[i]);
//            }
        int[] b = new int[prices.length];
        int[] s = new int[prices.length];
        b[0] = lastProfit[0] - prices[0];
        s[0] = 0;
        for (int i=1; i<prices.length; i++) {
            b[i] = Math.max(b[i-1], lastProfit[i]-prices[i]);
            s[i] = Math.max(s[i-1], b[i] + prices[i]);
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(2, new int[] {3,3,5,0,0,3,1,4}));
    }

}
