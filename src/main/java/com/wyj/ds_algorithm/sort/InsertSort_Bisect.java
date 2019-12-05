package com.wyj.ds_algorithm.sort;

/**
 * Created
 * Author: wyj
 * Date: 2019/12/5
 */
public class InsertSort_Bisect {

    public static void insertSort_Bisect(int[] arr) {
        int i=0;
        int j=i+1;

        while (j<=arr.length-1) {
            int insertValue = arr[j];
            int insertIndex = bisectFind(arr, 0, i, arr[j]);

            int k = j;

            while (k > insertIndex) {
                arr[k] = arr[k-1];
                k--;
            }

            arr[insertIndex] = insertValue;
            i++;
            j=i+1;
        }
    }

    public static int bisectFind(int[] arr, int start, int end, int value) {
        int mid = start;
        while (start <= end) {
            mid = (start + end) / 2;
            if (arr[mid] > value) {
                end = mid - 1;
            } else if (arr[mid] < value) {
                start = mid + 1;
            } else {
                return mid;
            }
        }

        return start;
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 2, 2, 3, 4, 5, 6, 6, 7, 7, 8, 9, 13, 24, 39, 55, 98};

        System.out.println(bisectFind(arr, 0, arr.length-1, 25));

    }
}
