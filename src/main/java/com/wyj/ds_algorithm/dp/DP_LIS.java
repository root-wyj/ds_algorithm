package com.wyj.ds_algorithm.dp;

/**
 * 最长递增子序列（Longest Increasing Subsequence）
 * 线性DP
 * LC 300
 * @author wuyingjie
 * Date: 2024/7/31
 */
public class DP_LIS {

    public static int lis(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int maxLength = 0;
        int[] dp = new int[nums.length];
        for (int i=0; i<nums.length; i++) {
            dp[i] = 1;
        }
        for (int i=1; i<nums.length; i++) {
            for (int j=0; j<i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(lis(new int[]{10,9,2,5,3,7,101,17,18}));
    }

}
