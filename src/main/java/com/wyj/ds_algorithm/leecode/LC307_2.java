package com.wyj.ds_algorithm.leecode;

import java.util.Arrays;

/**
 * 线段树
 * 参考自：https://blog.csdn.net/Miracle_ps/article/details/126563815
 */
public class LC307_2 {

    public static class SegmentTree {

        private int[] array;
        private int[] tree;

        public SegmentTree(int[] array) {
            this.array = array;
            tree = new int[array.length * 4];
            build(this.array, this.tree, 0, array.length-1, 1);
        }

        /**
         * nodeIndex 从1 开始，tree 一般申请 array 的4倍大小
         * tree[1] 保存的是 0-(n-1) 的最值
         */
        private void build(int[] array, int[] tree, int start, int end, int nodeIndex) {
            if (start == end) {
                tree[nodeIndex] = array[start];
            } else {
                int leftNodeIndex = nodeIndex * 2, rightNodeIndex = nodeIndex * 2 + 1;
                int mid = (start + end) / 2;
                build(array, tree, start, mid, leftNodeIndex);
                build(array, tree, mid + 1, end, rightNodeIndex);
                tree[nodeIndex] = Math.max(tree[leftNodeIndex], tree[rightNodeIndex]);
            }
        }

        private void update(int start, int end, int nodeIndex, int updateIndex, int updateVal) {
            if (start == end) {
                tree[nodeIndex] = array[updateIndex] = updateVal;
            } else {
                int mid = (start + end) / 2;
                int leftNodeIndex = nodeIndex * 2, rightNodeIndex = nodeIndex * 2 + 1;
                if (updateIndex <= mid) {
                    update(start, mid, leftNodeIndex, updateIndex, updateVal);
                } else {
                    update(mid+1, end, rightNodeIndex, updateIndex, updateVal);
                }
                tree[nodeIndex] = Math.max(tree[leftNodeIndex], tree[rightNodeIndex]);
            }
        }

        /**
         *
         * @param left 目标起
         * @param right 目标止
         * @param nodeIndex 从tree的哪个节点开始找，固定1
         * @param start nodeIndex 代表的起始位置
         * @param end nodeIndex 代表的终止位置
         * @return
         */
        public int query(int left, int right, int nodeIndex, int start, int end) {
            if (left == start && right == end) {
                return tree[nodeIndex];
            }
            int mid = (start + end) / 2;
            if (right <= mid) {
                // 那就去左边找
                return query(left, right, nodeIndex * 2, start, mid);
            } else if (left > mid) {
                // 去右边找
                return query(left, right, nodeIndex*2+1, mid + 1, end);
            } else {
                // 说明有交叉, 那就分成两个区间找
                return Math.max(query(left, mid, nodeIndex * 2, start, mid), query(mid + 1, right, nodeIndex*2+1, mid+1, end));
            }

        }


    }

    public static void main(String[] args) {
        int[] array = {3, 5, 8, 7, 4, 9};
        SegmentTree segmentTree = new SegmentTree(array);
        System.out.println(Arrays.toString(segmentTree.tree));
        System.out.println(segmentTree.query(1, 4, 1, 0, array.length - 1));
        System.out.println(segmentTree.query(4, 5, 1, 0, array.length - 1));
        segmentTree.update(0, array.length-1, 1, 4, 10);
        System.out.println(segmentTree.query(1, 4, 1, 0, array.length - 1));
        System.out.println(segmentTree.query(4, 5, 1, 0, array.length - 1));
    }

}
