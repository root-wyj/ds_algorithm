package com.wyj.ds_algorithm.divide_and_conquer;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 分治算法(divide-and-conquer)的设计思想：
 * <br><b>将一个难以解决的或者比较复杂的大的问题分成两个相同或者相似的子问题，再把子问题
 * 分成更小的子问题，直到最后子问题可以简单的直接求解，最后将子问题的解合并得到最后原问题的解</b>
 *
 * <br><br>适用分治算法的几个特征
 * <li>该问题缩小到一定的程度就可以很容易的解决</li>
 * <li>该问题可以分解为若干规模较小的相同的问题，该问题有<b>最优子结构性质</b></li>
 * <li>该问题的<b>子问题合并</b>之后可以得到原问题的解</li>
 * <li>该问题的子问题是相互独立的，即<b>子问题之间不包含公共的子子问题</b></li>
 *
 * <br>分治算法与动态规划的最大区别，在最后两条，<b>一是通过合并子问题的解可以得到原问题的解，二是子问题之间不包含公共的子子问题</b>
 *
 * <br><br>之前的快排，其实并没有合并子问题的解，只是将问题不断的小化。这里的归并排序就会将子问题合并，得到最后问题的解
 *
 * <br>归并排序思想：就是将两个有序的数组合并。而数组本来就是无序的，但是当数组的大小只有1的时候就是有序的。
 *
 * <br> 另外一个实例，100亿的数据中，找出前1000大。
 * <p>像这种比较大的数据一般都是用分治算法，将问题变小，解决之后，再合并。而且对于这种不包含公共子问题的问题（就是说子问题个算个的，没有什么影响），而且数据量又大，完全可以开启多个线程，并行计算。</p>
 *
 * @author wuyingjie
 * @date 2018年6月19日
 */

public class MergeSort {

	/**
	 * 将start-mid 看成一个数组，将mid+1 - end 看成一个数组，这两个数组 归并排序
	 * @param array
	 * @param start
	 * @param mid
	 * @param end
	 * @param <T>
	 */
	public static <T extends Comparable<T>> void mergeSort(T[] array, int start, int mid, int end, T[] tmpArray) {

		if (start < mid) {
			int mid1 = (start+mid)/2;
			mergeSort(array, start, mid1, mid, tmpArray);
		}

		if (mid+1 < end) {
			int mid2 = (mid+1+end)/2;
			mergeSort(array, mid + 1, mid2, end, tmpArray);
		}

		//经过上面两个步骤，start-mid的数组已经有序，mid+1 - end的数组已经有序，然后就是合并
		//将有序的结果合并到 临时数组里面
		int i = start, j=mid+1, p = 0;
		while(i<=mid && j<=end) {
			if (array[i].compareTo(array[j]) < 0) {
				tmpArray[p] = array[i];
				p ++;
				i ++;
			} else {
				tmpArray[p] = array[j];
				p ++;
				j ++;
			}
		}

		if (i > mid) {
			for (; j<=end; j++, p++) {
				tmpArray[p] = array[j];
			}
		}

		if (j > end) {
			for (; i<=mid; i++, p++) {
				tmpArray[p] = array[i];
			}
		}

		//将临时数组里的数据替换会原来数组
		for (int m=start, n=0; m<=end; m++, n++){
			array[m] = tmpArray[n];
		}

	}

	public static void main(String[] args) {
		Integer[] test = {15, 3, 8, 2, 59, 79, 2, 1, 26};
//		Integer[] test = {2, 1};
		mergeSort(test, 0, (0+test.length-1)/2, test.length-1, new Integer[test.length]);
		System.out.println(Arrays.toString(test));
	}

}
