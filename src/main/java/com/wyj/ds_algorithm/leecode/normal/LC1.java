package com.wyj.ds_algorithm.leecode.normal;

import java.util.HashSet;
import java.util.Set;

/**
 * 两数之和
 * @author wuyingjie
 * Date: 2024/7/29
 */
public class LC1 {
    public int[] twoSum(int[] nums, int target) {
        Set<Integer> set = new HashSet<>();
        for (int i=0; i<nums.length; i++) {
            if (set.contains(target - nums[i])) {
                return new int[]{i, target - nums[i]};
            }
            set.add(nums[i]);
        }
        return null;
    }

}
