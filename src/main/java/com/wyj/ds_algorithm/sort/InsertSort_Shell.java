package com.wyj.ds_algorithm.sort;

/**
 * 希尔排序--分步长的 插入排序
 * Created
 * Author: wyj
 * Date: 2019/12/5
 */
public class InsertSort_Shell {

    public static void insertSort_Shell(int[] arr) {

        int step = arr.length / 2;

        // 1. 分步
        while (step >= 1) {

            // 2. 每步长 插入排序
            for (int n=0; n<step; n++) {

                int start = n;
                int end = arr.length-1;
                int i = start;
                int j = start + step;

                while (j <= end) {
                    int insertValue = arr[j];
                    while (j-step >= 0 && insertValue < arr[j-step]) {
                        arr[j] = arr[j-step];
                        j -= step;
                    }
                    arr[j] = insertValue;

                    i = i + step;
                    j = i + step;
                }
            }

            step = step / 2;
        }



    }
}
