package com.wyj.ds_algorithm.dp;

/**
 * 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * @author wuyingjie
 * Date: 2024/8/1
 */
public class DP_LC64 {

    /*
    maze[m][n] 的最小路径和 就是 dp[m][n-1]+maze[m][n] 和 dp[m-1][n]+maze[m][n] 的最小值
    从上面 或者 从左边过来

    两种方式都行，自顶向下 就是上面的思路。
    自底向上 就是做个边界开始 一点点算
     */
    public static int minPathSum(int[][] grid) {
//        int sum = 0;
//        // 如果行 or 列 是1，则sum 就是结果
//        // 只有一行
//        if (grid.length == 1) {
//            for (int i=0; i<grid[0].length; i++) {
//                sum += grid[0][i];
//            }
//            return sum;
//        }
//
//        // 只有一列
//        if (grid[0].length == 1) {
//            for (int i=0; i<grid.length; i++) {
//                sum += grid[i][0];
//            }
//            return sum;
//        }

        // 既然是最小，就把墙(边界条件设置为最大)
        int[][] dp = new int[grid.length+1][grid[0].length+1];
        // 初始化列
        for (int i=0; i<grid.length+1; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }
        //初始化行
        for (int i=0; i<grid[0].length+1; i++) {
            dp[0][i] = Integer.MAX_VALUE;
        }

        // 设置dp
        for (int i=1; i<dp.length; i++) {
            for (int j=1; j<dp[i].length; j++) {
                int min = Math.min(dp[i - 1][j], dp[i][j - 1]);
                dp[i][j] = min == Integer.MAX_VALUE ? grid[i-1][j-1] : min + grid[i-1][j-1];
            }
        }

        return dp[grid.length][grid[0].length];
    }

    public static void main(String[] args) {
        int[][] hang = {{1,2,3}};
        int[][] lie = {{1},{2},{3}};
//        System.out.println(minPathSum(hang));
//        System.out.println(minPathSum(lie));
        System.out.println(minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
    }

}


