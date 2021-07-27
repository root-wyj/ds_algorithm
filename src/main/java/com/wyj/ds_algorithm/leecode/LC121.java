package com.wyj.ds_algorithm.leecode;

/**
 * 买卖股票的最佳时机
 * 一次买卖的 最大值
 * @author wuyingjie
 * Created on 2021-05-07
 */
public class LC121 {

    public static void main(String[] args) {

    }

    private static int fun(int[] array) {
        int sellMax = 0;
        int min = array[0];

        for (int i=1; i<array.length ;i++) {
            int gap = array[i] - min;
            if (gap > sellMax) {
                 sellMax = gap;
            } else if (gap < 0){
                min = array[i];
            }
        }
        return sellMax;
    }




}
