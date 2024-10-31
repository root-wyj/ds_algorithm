package com.wyj.ds_algorithm.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 */
public class LC15 {

    /**
     * 两个元素和为0 用hashMap
     * 三个元素之和为0 用有序+双指针
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        for (int first=0; first<nums.length; first++) {

            int target = -nums[first];
            for (int second=first+1; second<nums.length; second++) {
                int third = nums.length-1;

                while (third > second && nums[second] + nums[third] > target) {
                    third --;
                }

                if (third == second) {
                    continue;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> tmpList = new ArrayList<>();
                    tmpList.add(first);
                    tmpList.add(second);
                    tmpList.add(third);
                    ret.add(tmpList);
                }

            }


        }
        return null;
    }
}
