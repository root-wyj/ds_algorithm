package com.wyj.ds_algorithm.leecode;

/**
 * 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 * @author wuyingjie <13693653307@163.com>
 * Created on 2021-05-13
 */
public class LC42 {


    //[0,1,0,2,1,0,1,3,2,1,2,1]
    public int trap(int[] height) {
        int length = height.length;
        if (length <=1) {
            return 0;
        }

        int[] maxLeft = new int[length];
        int[] maxRight = new int[length];

        maxLeft[0] = height[0];
        maxRight[length - 1] = height[length - 1];

        for (int i=1; i<length; i++) {
            maxLeft[i] = Math.max(height[i], maxLeft[i-1]);
        }
        for (int i=length-2; i>=0; i--) {
            maxRight[i] = Math.max(maxRight[i+1], height[i]);
        }
        int sum = 0;
        for (int i=0; i<length; i++) {
            sum += Math.min(maxLeft[i], maxRight[i]) - height[i];
        }

        return sum;
    }

}
