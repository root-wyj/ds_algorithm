package com.wyj.ds_algorithm.sort;

/**
 * Created
 * Author: wyj
 * Date: 2019/12/4
 */
public class SortUtils {

    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
