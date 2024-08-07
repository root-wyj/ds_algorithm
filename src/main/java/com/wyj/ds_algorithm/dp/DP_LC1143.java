package com.wyj.ds_algorithm.dp;

/**
 * 最长公共子序列（Longest Common Subsequence，简称 LCS
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace"，它的长度为 3。
 * @author wuyingjie
 * Date: 2024/8/1
 */
public class DP_LC1143 {


    public static int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()+1][text2.length()+1];

        // 初始化 第0行 第0列，空字符串和任何字符串 都没有最长公共子序列
        for (int i=0; i<text2.length()+1; i++) {
            dp[0][i] = 0;
        }
        for (int i=0; i<text1.length()+1; i++) {
            dp[i][0] = 0;
        }

        for (int i=1; i<text1.length()+1; i++) {
            char c1 = text1.charAt(i-1);
            for (int j=1; j<text2.length()+1; j++) {
                char c2 = text2.charAt(j-1);

                if (c1 == c2) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = max(dp[i-1][j-1], dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        return dp[text1.length()][text2.length()];

    }

    private static int max(int ...a) {
        int max = a[0];
        for (int i=1; i<a.length; i++) {
            max = Math.max(max, a[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcde", "ace"));
    }

}
