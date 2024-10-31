package com.wyj.ds_algorithm.leecode;

/**
 * 给你一个数组 nums ，请你完成两类查询。
 *
 * 其中一类查询要求 更新 数组 nums 下标对应的值
 * 另一类查询要求返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 ，其中 left <= right
 */
public class LC307 {

    static class NumArray {

        int[] nums;
        int[] tree;

        int n;

        int lowbit(int x) {
            return x&-x;
        }

        void add(int x, int val) {
            for (int i = x; i <= n; i += lowbit(i))
                tree[i] += val;
        }

        int query(int x) {
            int ans = 0;
            for (int i = x; i > 0; i -= lowbit(i))
                ans += tree[i];
            return ans;
        }

        public NumArray(int[] nums) {
            this.nums = nums;
            n = nums.length;
            tree = new int[n + 1];
            for (int i=0; i<n; i++) {
                add(i+1, nums[i]);
            }
        }

        public void update(int index, int val) {

        }

        public int sumRange(int left, int right) {
            return 0;
        }
    }

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5};
        NumArray numArray = new NumArray(array);
        numArray.query(3);
        numArray.query(4);
    }
}
