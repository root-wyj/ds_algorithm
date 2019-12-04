package com.wyj.ds_algorithm.sort;

/**
 * 不交换机，直接填充
 *
 * Created
 * Author: wyj
 * Date: 2019/12/4
 */
public class QSort_Fill {


    public static void qSort_Fill(int[] array) {
        sort(array, 0, array.length -1);
    }

    public static void sort(int[] array, int start, int end) {

        if (start >= end) return;

        int pivot = array[start];
        int i = start;
        int j = end;

        while (i < j) {
            // 找到了 右边 小于 pivot的，或者相等了
            while (i < j && pivot < array[j]) {
                j--;
            }
            array[i] = array[j];


            while (i < j && pivot >= array[i]) {
                i++;
            }
            array[j] = array[i];
        }

        // 出口 一定是 i==j
        array[j] = pivot;

        sort(array, start, j-1);
        sort(array, j+1, end);
    }

}
