package com.wyj.ds_algorithm.sort;

/**
 * Created
 * Author: wyj
 * Date: 2019/12/5
 */
public class SwapSort_Cocktail {

    public static void swapSort_Cocktail(int[] arr) {

        int start = 0, end = arr.length - 1;
        int k = start;

        while (start < end) {

            for (int i = start; i < end; i++) {
                if (arr[i] > arr[i+1]) {
                    SortUtils.swap(arr, i, i+1);
                }
            }
            end--;
            for (int i = end; i > start; i--) {
                if (arr[i] < arr[i-1]) {
                    SortUtils.swap(arr, i, i-1);
                }
            }
            start++;
        }

    }

}
