package com.wyj.ds_algorithm.sort;

/**
 * 快排，也就是常说的单轴快排(SinglePivotQuickSort)，
 * 选一个数，从另一边开始，以交换或者覆盖的方式 将大于的放到右边，小于的放到左边。
 *
 * 快排的关键就在于 怎么快速的把大于或者小于目标值的数据 分配到两端
 * Created
 * Author: wyj
 * Date: 2019/12/4
 */
public class QSort {

//    public static <T extends Comparable<T>> void qSort(T[] array) {
//
//    }

    public static void qSort(int[] array) {
        sort(array, 0, array.length-1);
    }

    private static void sort(int[] array, int start, int end) {
        // 1个数
        if (start >= end) return;

        int pivot = array[start];
        int n = start+1;
        int m = end;

        while (n <= m) {

            // 找到右边 第一个小的

            // = 的场景 想的是 右边一直减 减到了和左边相等的情况，如果没有= n 就出现了没有处理的情况。
            while (n <= m && pivot < array[m]) {
                m--;
            }

            // 找到左边 第一个大的
            while (n <= m && pivot >= array[n]) {
                n++;
            }

            if (n <= m) {
                SortUtils.swap(array, n, m);
            }
        }

        // 将中心点的数据和start 交换，中心点 总是 后面的那个？？？
        // 和m交换 是因为 pivot 在左边，m最后的位置一定是 小于pivot的，所以m和pivot交换后，满足 pivot 左边的 小于 它
        SortUtils.swap(array, start, m);

        sort(array, start, m-1);
        sort(array, m+1, end);
    }



}
