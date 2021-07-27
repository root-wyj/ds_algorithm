package com.wyj.ds_algorithm.leecode;

/**
 * 最长公共子序列
 *
 * abc,acdbe 的最长公共子序列是 ab 或ac 是2
 * if (str[m] == str[n]) {
 *     f(m,n) = f(m-1, n-1) + 1;
 * } else {
 *     f(m,n) = Math.max(f(m, n-1), f(m-1, n));
 * }
 *
 * @author wuyingjie <13693653307@163.com>
 * Created on 2021-05-18
 */
public class LC1143 {

    // "oxcpqrsvwf"
    //"shmtulqrypy"
    public static void main(String[] args) {
        System.out.println(new LC1143().longestCommonSubsequence("oxcpqrsvwf", "shmtulqrypy"));
    }

    public int longestCommonSubsequence(String text1, String text2) {

        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        for (int i=1; i<=text1.length(); i++) {

            for (int j=1; j<=text2.length(); j++) {

                if (text1.charAt(i-1) == text1.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    public int longest(String text1, String text2, int m, int n) {
        if (m == text1.length() || n == text2.length()) {
            return 0;
        }

        for (int i=m;i <text1.length(); i++) {
            for (int j=n; j<text2.length(); j++) {

                if (text1.charAt(i) == text2.charAt(j)) {
                    return longest(text1, text2, i+1, j+1) + 1;
                }

            }
        }

        return 0;

    }



}
