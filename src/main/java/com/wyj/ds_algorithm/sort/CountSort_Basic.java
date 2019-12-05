package com.wyj.ds_algorithm.sort;

/**
 * Created
 * Author: wyj
 * Date: 2019/12/5
 */
public class CountSort_Basic {

    public static void countSort_Basic(int[] arr) {
        int min = arr[0], max = arr[0];
        for (int i=0; i<arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }

            if (arr[i] > max) {
                max = arr[i];
            }
        }
        countSort_Basic(arr, min, max);
    }

    public static void countSort_Basic(int[] arr, int min, int max) {

        // 用来保存结果的
        int[] result = new int[arr.length];

        // 用来计数的 工具数组
        int[] counter = new int[max-min+1];

        for (int i=0; i<arr.length; i++) {
            // 给这个元素 所在的 counter 的index 的值++，也就是计数
            counter[arr[i]-min]++;
        }

        // 求位置，索引的值 <= 的个数
        for (int i=0; i<counter.length-1; i++) {
            counter[i+1] = counter[i] + counter[i+1];
        }


        // 要从后 往前 赋值
        for (int i=arr.length-1; i>=0; i--) {
            int value = arr[i];
            // counter 最后一个值 会统计到所有的元素的个数，比index大了1
            result[counter[value-min]-1] = value;
            counter[value-min]--;
        }


        System.arraycopy(result, 0, arr, 0, result.length);
    }
}
