package com.wyj.ds_algorithm.dp.beibao;

import java.util.HashMap;
import java.util.Map;

/**
 * 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 *
 *
 * 思路转换，那就是找里面的元素 是否能加起来等于总和的一半
 *
 * 0 1背包问题
 *
 * @author wuyingjie
 * Date: 2024/8/1
 */
public class DP_LC416 {

    private static int recoverCnt = 0;

    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i=0; i<nums.length; i++) {
            sum += nums[i];
        }
        int divide = sum / 2 ;
        if (sum % 2 == 1) {
            return false;
        }
        // index , coin , result
        Map<Integer, Map<Integer, Boolean>> records = new HashMap<>();
        return doCan(nums, divide, nums.length - 1, records);
    }

    public static boolean doCan(int[] nums, int sum, int index, Map<Integer, Map<Integer, Boolean>> records) {
        Boolean result = get(records, index, sum);
        if (result != null) {
            recoverCnt++;
            System.out.println("用到了检索表：index:" + index + ", sum:" +sum+ ", result:" + result);
            return result;
        }

        if (sum == 0) {
            put(records, index, sum, true);
            return true;
        }
        if (index == -1) {
            put(records, index, sum, false);
            return false;
        }
        if (nums[index] > sum) {
            result = doCan(nums, sum, index - 1, records);
            put(records, index, sum, result);
            return result;
        } else {
            boolean contains = doCan(nums, sum-nums[index], index-1, records);
            boolean notContains = doCan(nums, sum, index -1, records);
            result = contains || notContains;
            put(records, index, sum, result);
            return result;
        }
    }

    private static void put(Map<Integer, Map<Integer, Boolean>> records, int index, int sum, boolean result) {
        records.computeIfAbsent(index, k -> new HashMap<>());
        records.get(index).put(sum, result);
    }

    private static Boolean get(Map<Integer, Map<Integer, Boolean>> records, int index, int sum) {
        Map<Integer, Boolean> map = records.get(index);
        if (map != null) {
            return map.get(sum);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(canPartition(new int[] {1,5,11,5}));
        System.out.println(canPartition(new int[] {1,2,3,5}));
        System.out.println();
        System.out.println(canPartition(new int[]{100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,99,97}));
        System.out.println(recoverCnt);
    }
}
