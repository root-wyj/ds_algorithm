package com.wyj.ds_algorithm.sort;

/**
 * 简单插入排序，前面认为有序的，从第二个开始 向前遍历，找合适的位置插入
 * Created
 * Author: wyj
 * Date: 2019/12/5
 */
public class InsertSort_Simple {


    public static void insertSort_Simple(int[] arr) {

        int i = 0;// 最后一个有序的index
        int j = i+1; // 需要插入的元素

        while (j < arr.length) {
            int insertValue = arr[j];

            int n = i;

            while (n>=0) {

                if (insertValue < arr[n]) {
                    arr[n + 1] = arr[n];
                    n--;
                } else {
                    break;
                }

            }
            arr[n + 1] = insertValue;
            i++;
            j=i+1;
        }
    }



}
