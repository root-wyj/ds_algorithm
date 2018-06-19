package com.wyj.ds_algorithm.divide_and_conquer;

import java.util.Arrays;

/**
 * 
 * @author wuyingjie
 * @date 2018年6月19日
 */

public class QSort {
	
	public static <T extends Comparable<T>> void qsort(T[] array, int start, int end) {
		if (start == end) return;
		
		int cur = end;
		int mid = start;
		while (cur != mid) {
			if (cur < mid) {
				if (array[cur].compareTo(array[mid]) > 0) {
					swap(array, cur, mid);
					int tmp = cur;
					cur = mid;
					mid = tmp;
					
					cur = cur - 1;
				} else {
					cur = cur + 1;
				}
			}
			if (cur > mid) {
				if (array[cur].compareTo(array[mid]) < 0) {
					swap(array, cur, mid);
					int tmp = cur;
					cur = mid;
					mid = tmp;
					
					cur = cur + 1;
				} else {
					cur = cur - 1;
				}
			}
		}
		if (mid == start) {
			qsort(array, start+1, end);
		} else if (mid == end) {
			qsort(array, start, end-1);
		} else {
			qsort(array, start, mid-1);
			qsort(array, mid+1, end);
		}
	}
	
	private static void swap(Object[] array, int indexa, int indexb) {
		Object tmp = array[indexa];
		array[indexa] = array[indexb];
		array[indexb] = tmp;
	}
	
	public static void main(String[] args) {
		Integer[] test = {15, 3, 8, 2, 59, 79, 2, 1, 26};
//		Integer[] test = {2, 1};
		qsort(test, 0, test.length-1);
		System.out.println(Arrays.toString(test));
	}
	
}
