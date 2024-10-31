package com.wyj.ds_algorithm.leecode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 最长连续序列，要求时间复杂度O(n)
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 */
public class LC128 {

    /**
     * 思路，如果是个有序的 怎么找？
     * 如果n+1就是下一个元素，那就找
     * 如果n+1 不是下一个元素，那就说明找完了。下一个元素 开始新一轮的找
     *
     * 如果某个元素前面还有元素，结果就是不可信的，所以问题的第一个关键点 是区分出来起步的元素。
     *
     * 起步的元素的n-1 不存在。
     *
     * 第二点，题目要求了时间复杂度，那就从空间复杂度上找补回来。使用hashmap 去判断 上一个元素 存不存在
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        Arrays.stream(nums).forEach(set::add);
        int maxLength = 0;
        for (int i=0; i<nums.length; i++) {
            // n-1 不在，说明是序列的第一个元素，可以开始找了
            if (!set.contains(nums[i] - 1)) {
                int number = nums[i] + 1;
                while (set.contains(number)) {
                    number++;
                }
                maxLength = Math.max(maxLength, number-nums[i]);
            }
        }
        return maxLength;
    }

}
