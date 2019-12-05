package com.wyj.ds_algorithm.sort;

import java.util.Arrays;

/**
 * Created
 * Author: wyj
 * Date: 2019/12/5
 */
public class CountSort_Radix {


    public static void countSort_Radix(int[] array) {
        countSort_Radix(array, 3, 10);
    }

    public static void countSort_Radix(int[] array, int maxLength, int radix) {

        int[] counter = new int[radix];
        int[] result = new int[array.length];


        // 第一遍 个位数有序
        // 第二遍 十位数有序，对于之前的个位数 依次入位
        for (int m=0, r=1; m<maxLength; m++,r=r*radix) {
            Arrays.fill(counter, 0);

            for (int i=0; i<array.length; i++) {
                int indexValue = (array[i] / r) % radix;
                counter[indexValue]++;
            }

            for (int i=0; i<counter.length-1; i++) {
                counter[i+1] = counter[i] + counter[i+1];
            }

            for (int i=array.length-1; i>=0; i--) {
                int indexValue = (array[i] / r) % radix;
                result[counter[indexValue]-1] = array[i];
                counter[indexValue]--;
            }

            System.arraycopy(result, 0, array, 0, array.length);
        }
    }


}
