package com.wyj.ds_algorithm.dp;

/**
 * 最大子数组和(Maximum Subarray) - 线性DP
 * LC 53
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6。
 * @author wuyingjie
 * Date: 2024/7/31
 */
public class DP_LC53 {

    /**
     * 拆解最优子结构 + 状态转移
     * 1. 大数组 可以拆解为 小数组一个一个数的增加。
     * 2. 最终的结果一定是以某个元素结束，所以以dp[i]表示以这个元素结尾的最大子数组的和
     * 3. 状态转移方程：dp[i] = max(dp[i-1] + nums[i], nums[i])
     * @param nums
     * @return
     */
    private static int maximumSubarray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int maxSum = nums[0];
        for (int i=1; i<nums.length; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }
    /*
    动态规划的实现方式有两种，一种是自顶向下，一种是自底向上。
    自顶向下，采用记忆化搜索的方式，由于是自顶向下，所以会有很多的重复子问题，所以需要有一个记忆表(Map)，用来存储已经计算过的子问题的最优解
    自底向上，从底部开始，记录每个子问题的解，避免重复计算。
     */

    public static void main(String[] args) {
        System.out.println(maximumSubarray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}
