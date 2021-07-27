package com.wyj.ds_algorithm.leecode;

/**
 * 搜索旋转有序数组
 * 如 [0,1,2,4,6,7] 按照下标3旋转之后得到 [4,6,7,0,1,2]
 * 根据这个来搜索 某个数在数组中的下标。
 *
 * @authoright wuyingjie
 * Created on 2021-05-08
 */
public class LC33 {


    public static void main(String[] args) {
        int[] arr = {4,5,6,7,8,1,2,3};
        System.out.println(find(arr, 8));
    }


    /*
    判断k是否在两个 有序的数组之间，比如 arr[mid] > arr[0] 说明arr[0] - arr[mid]是有序的，则先看k是否在这之间，如果是，就在这里面找，不是 在另外的里面找。
     */
    private static int find(int[] array, int k) {

        if (array.length == 0) {
            return -1;
        }

        int left = 0, right = array.length-1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;

            if (array[mid] == k) {
                return mid;
            }

            if (array[mid] >= array[0]) {
                if (array[mid] > k && k >= array[0]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (array[mid] < k && k <= array[array.length - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

        }

        return -1;
    }

    private static int findErfen(int[] array, int k) {
        int left = 0, right = array.length - 1;
        int mid = (left + right) / 2;
        while (left <= right) {
            mid = (left + right) / 2;
            if (array[mid] < k) {
                left = mid + 1;
            } else if (array[mid] > k){
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

}
