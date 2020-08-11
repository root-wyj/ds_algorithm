package com.wyj.ds_algorithm.dp;

import java.util.Arrays;

/**
 * 求一个数据的 最长 递增(包括等于)子序列的最大长度(可以不连续)
 * {10, 20, 10, 11, 12, 13, 14} 为6
 * @author wuyingjie <13693653307@163.com>
 * Created on 2020-07-31
 */
public class DP_SubList {



    public static void main(String[] args) {
        int[] arr1 = {10, 20, 10, 11, 12, 13, 14};
        int[] arr2 = {13, 15, 17, 20, 10, 11, 12, 21};
        maxLengthOfList(arr1);
        maxLengthOfList(arr2);

    }

    public static int maxLengthOfList(int[] arr) {
        if (arr.length == 1) return 1;
        int[] lenArr = new int[arr.length];
        lenArr[0] = 1;
        int maxIndex = 0;

        for (int i=1; i<arr.length; i++) {
            if (arr[i] >= arr[maxIndex]) {
                lenArr[i] = lenArr[maxIndex] + 1;
            } else {
                // 赋默认值
                lenArr[i] = 1;
                for (int j=0; j<i; j++) {
                    if (arr[i] >= arr[j]) {
                        lenArr[i] = Math.max(lenArr[j] + 1, lenArr[i]);
                    }
                }
            }
            if (lenArr[i] > lenArr[maxIndex]) {
                maxIndex = i;
            }
        }
        System.out.println(Arrays.toString(lenArr));

        return lenArr[maxIndex];
    }



}
