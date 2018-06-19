package com.wyj.ds_algorithm.dp;

import java.util.Arrays;

/**
 * 动态规划之 找零钱
 * 有许多面值的零钱 V1,V2,V3,V4...，然后用最少的张数凑够某个钱数
 * 比如说有面值1,2,5,21,25 中找出金额为63需要的最小张数
 * 
 * 后来把 由哪些零钱组成的数据也找出来了
 * @author wuyingjie
 * @date 2018年6月19日
 */

public class DP_ZhaoLingQian {
	
	static int hitCount = 0;
	
	// 最多支持10张不同的零钱
	private int[] change = new int[10];
	
	private int changeCount;
	
	// 最大支持 100 块
	private int[][] table = new int[100 + 1][100+1];
	
	/**
	 * 函数返回 当前钱数 兑换成零钱最少使用的张数和兑换的零钱数组
	 * @param curAmount
	 * @return
	 */
	public Object[] optimalResolution(int curAmount) {
		int result = table[curAmount][0];
		
		// 说明之前已经计算过了
		if (result != 0) {
			hitCount++;
			// 注意 这里需要重新构造需要返回的数据数组
			//如果直接返回 ，返回的是[data, data, 0, 0, ..]这种数据，有很多无用的数据 和函数定义不符，导致后来的计算错误
			int[] items = new int[table[curAmount][0]];
			for (int i=0; i<items.length; i++) {
				items[i] = table[curAmount][i+1];
			}
			return new Object[]{result, items};
		}
		
		// 第一维 用来保存 用了多少张零钱，第二维 用来保存用了哪些零钱
		int[][] max_ = new int[changeCount][100+1];
		
		for (int i=0; i<changeCount; i++) {
			if (curAmount > change[i]) {
				Object[] oList = optimalResolution(curAmount-change[i]);
				max_[i][0] = (int)(oList[0]) + 1;
				
				int[] b = (int[])oList[1];
				max_[i][1] = change[i];
				for (int j=0; j<(int)(oList[0]); j++) {
					max_[i][j+2] = b[j];
				}
			} else if (curAmount == change[i]){
				max_[i][0] = 1;
				max_[i][max_[i][0]] = change[i];
			} else {
				// 设置一个比较大的值 意思是 直接舍弃
				max_[i][0] = changeCount;
			}
		}
		
		// 默认第一个是最小的 从index=1 开始比较
		for (int i=1; i<max_.length; i++) {
			if (max_[i][0] < max_[0][0]) {
				max_[0][0] = max_[i][0];
				
				// 也要替换 由哪些数据组成的
				for (int j=1; j<=max_[i][0]; j++) {
					max_[0][j] = max_[i][j];
				}
			}
		}
		
		// 将这些数据保存在 table中
		for (int j=0; j<=max_[0][0]; j++) {
			table[curAmount][j] = max_[0][j];
		}
		
		//重新构造需要返回的数据的数组 
		int[] items = new int[table[curAmount][0]];
		for (int i=0; i<items.length; i++) {
			items[i] = table[curAmount][i+1];
		}
		
		return new Object[] {table[curAmount][0], items};
	}
	
	public DP_ZhaoLingQian(int[] change) {
		changeCount = change.length;
		for (int i=0; i<changeCount; i++) {
			this.change[i] = change[i];
		}
	}
	
	public static void main(String[] args) {
		int change[] = {25, 21, 5, 2, 1};
		DP_ZhaoLingQian zhaoLingQian = new DP_ZhaoLingQian(change);
		Object[] result = zhaoLingQian.optimalResolution(100);
		System.out.println(result[0]);
		System.out.println(Arrays.toString((int[])result[1]));
		System.out.println(Arrays.toString(zhaoLingQian.table[63]));
		System.out.println(Arrays.toString(zhaoLingQian.table[53]));
		System.out.println(hitCount);
	}
	
	
}
