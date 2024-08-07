package com.wyj.ds_algorithm.dp;

/**
 * 将word1变成word2的最短编辑距离，插入，删除，替换
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 * @author wuyingjie
 * Date: 2024/8/1
 */
public class DP_LC72 {

    public static int minDistance(String word1, String word2) {
        if (word1.length() == 0 || word2.length() == 0) {
            return Math.max(word1.length(), word2.length());
        }

        int[][] dp = new int[word1.length()][word2.length()];
        dp[0][0] = word1.charAt(0) == word2.charAt(0) ? 0 : 1;

        // 初始化 第一行 word2 -> word1[0]
        boolean contains = dp[0][0] == 0;
        for (int i=1; i<word2.length(); i++) {
            if (!contains && word2.charAt(i) == word1.charAt(0)) {
                contains = true;
                dp[0][i] = dp[0][i-1];
            } else {
                dp[0][i] = dp[0][i-1] + 1;
            }
        }

        contains = dp[0][0] == 0;
        for (int i=1; i<word1.length(); i++) {
            if (!contains && word1.charAt(i) == word2.charAt(0)) {
                contains = true;
                dp[i][0] = dp[i-1][0];
            } else {
                dp[i][0] = dp[i-1][0] + 1;
            }
        }
        for (int i=1; i<word1.length(); i++) {
            char cw1 = word1.charAt(i);
            for (int j=1; j<word2.length(); j++) {
                char cw2 = word2.charAt(j);
                if (cw1 == cw2) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    // 新增到A中一个字符 新增到B中一个字符 AB字符替换 三种方式的最小值
                    dp[i][j] = min(dp[i][j-1] + 1, dp[i-1][j] + 1, dp[i-1][j-1] + 1);
                }
            }
        }

        return dp[word1.length()-1][word2.length()-1];

    }

    /**
     * 相比于上一个方法，把边界值 作为一道墙初始化了出来。
     * 相当于多了一步初始化的准备工作，少了 各种边界情况的判断
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance2(String word1, String word2) {
        if (word1.length() == 0 || word2.length() == 0) {
            return Math.max(word1.length(), word2.length());
        }

        // 整一个 大于word1 和 word2的二维数组
        int[][] dp = new int[word1.length()+1][word2.length()+1];

        // 初始化 第0行 第0列，认为是一个 空单词到 某个单词 需要做的操作
        // 第0行
        for (int i=0; i<word2.length()+1; i++) {
            dp[0][i] = i;
        }
        // 第0列
        for (int i=1; i<word1.length()+1; i++) {
            dp[i][0] = i;
        }
        for (int i=1; i<word1.length()+1; i++) {
            char cw1 = word1.charAt(i-1);
            for (int j=1; j<word2.length()+1; j++) {
                char cw2 = word2.charAt(j-1);
                if (cw1 == cw2) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = min(dp[i][j-1] + 1, dp[i-1][j] + 1, dp[i-1][j-1] + 1);
                }
            }
        }
        return dp[word1.length()][word2.length()];

    }

    private static int min(int ...a) {
        int min = a[0];
        for (int i=1; i<a.length; i++) {
            min = Math.min(min, a[i]);
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros"));
        System.out.println(minDistance2("horse", "ros"));
    }
}
