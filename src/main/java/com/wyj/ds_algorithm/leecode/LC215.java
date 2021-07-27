package com.wyj.ds_algorithm.leecode;

/**
 * 数组中的第K大元素
 * @author wuyingjie <13693653307@163.com>
 * Created on 2021-05-11
 */
public class LC215 {

    public static void main(String[] args) {
        LC215 lc215 = new LC215();
        int[] nums = {2};
        System.out.println(lc215.findKthLargest(nums, 1));
    }

    int myK = 0;

    public int findKthLargest(int[] nums, int k) {
        qSort(nums, 0, nums.length - 1, k);
        return myK;
    }

    private void qSort(int[] nums, int start, int end, int k) {
        if (start >= end) {
            if (nums.length - start == k) {
                myK = nums[start];
            }

            if (nums.length - end == k) {
                myK = nums[end];
            }
            return;
        }

        int pivot = start;
        int i = start+1;
        int j = end;

        while (i<=j) {
            while (i<=j && nums[pivot] <= nums[j]) {
                j--;
            }

            while (i<=j && nums[pivot] > nums[i]) {
                i++;
            }

            if (i<=j) {
                swap(nums, i, j);
            }
        }

        swap(nums, pivot, j);
        int maxK = nums.length - j;
        if (maxK == k) {
            myK = nums[j];
        } else if (maxK > k) {
            qSort(nums, j+1, end, k);
        } else {
            qSort(nums, start, j - 1, k);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
