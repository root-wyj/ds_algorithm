package com.wyj.ds_algorithm.leecode;

import java.util.LinkedList;
import java.util.Queue;

import com.wyj.ds_algorithm.TreeNode;

/**
 * 将有序数组 转化为 平衡二叉搜索树
 * @author wuyingjie <13693653307@163.com>
 * Created on 2021-05-10
 */
public class LC108 {

    private TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return new TreeNode(0, null,  null);
        } else {
            return toBST(nums, 0, nums.length - 1);
        }
    }

    private TreeNode toBST(int[] nums, int start, int end) {
        if (start == end) {
            return new TreeNode(nums[start], null, null);
        }
        int i = (start + end) / 2;
        if (start == i) {
            return new TreeNode(nums[start], null, new TreeNode(nums[end], null, null));
        } else if (end == i) {
            return new TreeNode(nums[end], new TreeNode(nums[start], null, null), null);
        } else {
            return new TreeNode(nums[i], toBST(nums, start, i-1), toBST(nums, i+1, end));
        }
    }


    /**
     * 以nums[0]为根节点 将数组转化为树
     */
    public TreeNode sortedArrayToBST2(int[] nums) {
        TreeNode root = new TreeNode(nums[0], null, null);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 0;
        while (true) {
            TreeNode poll = queue.poll();
            i++;
            if (i == nums.length) {
                // 走完了
                break;
            }
            poll.left = new TreeNode(nums[i], null, null);
            queue.offer(poll.left);


            i++;
            if (i == nums.length) {
                // 走完了
                break;
            }
            poll.right = new TreeNode(nums[i], null, null);
            queue.offer(poll.right);
        }
        return root;
    }

}
