package com.wyj.ds_algorithm.sort;

/**
 * 堆排序，根据堆的特性，以类似冒泡的 分治思想，实现最终数据的有序
 * 大根堆：父节点比子节点大
 * 小根堆：父节点比子节点小
 * 这篇讲的不错 https://blog.csdn.net/u010452388/article/details/81283998
 * 从小到大 要构造大根堆
 * @author wuyingjie
 * Created on 2021-05-07
 */
public class HeapSort {


    public static void heapSort(int[] array) {
        for (int i=array.length-1; i>=1; i--) {
            buildBigHeap(array, i);
            SortUtils.swap(array, 0, i);
        }
    }

    private static void buildBigHeap(int[] array, int length) {
        for (int i=1; i<=length; i++) {
            int childIndex = i;
            int parentIndex = (childIndex-1) / 2;

            while (array[parentIndex] < array[childIndex]) {
                SortUtils.swap(array, parentIndex, childIndex);
                childIndex = parentIndex;
                parentIndex = (childIndex - 1) / 2;
            }
        }
    }





}
