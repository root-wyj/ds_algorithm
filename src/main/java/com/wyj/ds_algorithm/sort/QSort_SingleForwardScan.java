package com.wyj.ds_algorithm.sort;

/**
 * 2 分单向扫描。这种和之前的思路有点不一样。
 *
 * 有start, end, i=start+1, j为索引扫描，让 start--i 都是 <=, i+1--j-1 都是 > 的 j--end 都是待扫描的
 *
 * 之前 i，j相向而行，现在 同向而行，
 *
 * 最后 将 start 和 i 交换位置就好了。
 *
 * <br/>拓展之三分单向扫描，针对于 相同元素比较多的情况下，用专门的一个区间存储 = 的元素。 增加 k，标志 = 的区间
 *
 * <br/>Created
 * Author: wyj
 * Date: 2019/12/4
 */
public class QSort_SingleForwardScan {


    public static void qSort_SingleForwardScan(int[] arr) {
        qSort_SingleForwardScan(arr, 0, arr.length - 1);
    }

    public static void qSort_SingleForwardScan(int[] arr, int start, int end) {
        if (start >= end) return;

        int pivot = arr[start];
        int i = start;
        int j = i+1;


        while (j <= end) {
            if (arr[j] <= pivot) {
                i++;
                SortUtils.swap(arr, i, j);
            }

            j++;
        }

        SortUtils.swap(arr, start, i);

        qSort_SingleForwardScan(arr, start, i-1);
        qSort_SingleForwardScan(arr, i+1, end);
    }

}
