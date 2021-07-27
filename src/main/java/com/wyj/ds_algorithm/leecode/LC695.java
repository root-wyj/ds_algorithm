package com.wyj.ds_algorithm.leecode;

/**
 * 岛屿的最大面积
 *
 *
 *
 * //TODO 糖果题
 *
 * @author wuyingjie <13693653307@163.com>
 * Created on 2021-05-13
 */
public class LC695 {

    int max = 0;

    public int maxAreaOfIsland(int[][] grid) {
        int maxSize = 0;
        for (int i=0; i<grid.length; i++) {

            for (int j=0; j<grid[0].length; j++) {

                int curMax = maxArea(grid, i, j);
                max = Math.max(max, curMax);
            }

        }
        return max;
    }

    /**
     * 这种是 使用递归计算最大面积。想要计算以 i,j 为起点的，就需要 往4个方向扩展，并求和
     *
     * 扩展思想是说，把递归换成栈，将4个方向 符合要求的 都入栈。
     *
     */
    private int maxArea(int[][] grid, int m, int n) {
        if (m < 0 || m >= grid.length) {
            return 0;
        }

        if (n < 0 || n >= grid[m].length) {
            return 0;
        }

        if (grid[m][n] == 0) {
            return 0;
        }
        grid[m][n] = 0;
        return 1 + maxArea(grid, m + 1, n) + maxArea(grid, m-1, n) + maxArea(grid, m, n+1) + maxArea(grid, m, n-1);
    }


}
