package com.wyj.ds_algorithm.dp;

import java.util.Arrays;

/**
 * 最长递增子序列
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 *
 * 测试用例 [9,2,5,3,101,1,6]
 */
public class DP_LC300 {

    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int ret = 1;
        for (int i=1; i< nums.length; i++) {
            for (int j=0; j<i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ret = Math.max(dp[i], ret);
        }
        return ret;
    }

    public static void main(String[] args) {
        lengthOfLIS(new int[] {9,2,5,3,101,1,6});
    }



}
