package com.wyj.ds_algorithm.sort;

/**
 * Created
 * Author: wyj
 * Date: 2019/12/5
 */
public class MergeSort_Basic {

    public static void mergeSort_Basic(int[] arr) {
        int[] tmp = new int[arr.length];
//        System.arraycopy(arr, 0, tmp, 0, arr.length);
        mergeSort_Basic(arr, tmp, 0, arr.length - 1);

    }


    /**
     * 有回溯的思想
     * 1. 分治，分为有序的
     * 2. 合并 有序的
     * 先把大问题分解为小问题，
     * 然后 小问题 直接就解决了，
     * 然后 汇总 解决小问题，
     * 汇总着，就解决了大问题，最后解决了问题
     */
    public static void mergeSort_Basic(int[] arr, int[] tmp, int start, int end) {

        // 短的有序数组的 出口
        if (start == end) {
            return;
        }

        if (start + 1 == end && arr[start] > arr[end]) {
            SortUtils.swap(arr, start, end);
            return;
        }


        // 1. 分为有序的
        // 这一步 1-2 或者 2-2的会被组合起来 进行下一步，下一步 就是将这个合并，就变成了 3-4的有序的
        int mid = (start + end) / 2;
        mergeSort_Basic(arr, tmp, start, mid);
        mergeSort_Basic(arr, tmp, mid + 1, end);


        // 2. 合并有序的 start-mid 和 (mid+1)-end
        int i = start; //数组1 的开始位置
        int j = mid+1; //数组2 的开始位置
        int k = start; //目标数组的开始位置

        while (i <= mid && j<=end) {
            if (arr[i] <= arr[j]) {
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            tmp[k++] = arr[i++];
        }

        while (j <= end) {
            tmp[k++] = arr[j++];
        }

        System.arraycopy(tmp, start, arr, start, end-start+1);
    }
}
