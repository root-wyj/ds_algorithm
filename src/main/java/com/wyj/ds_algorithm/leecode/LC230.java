package com.wyj.ds_algorithm.leecode;

import com.wyj.ds_algorithm.TreeNode;

/**
 * 二叉搜索树中的 第k小元素
 *
 * @author wuyingjie <13693653307@163.com>
 * Created on 2021-05-11
 */
public class LC230 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, new TreeNode(1, null, new TreeNode(2, null, null)), new TreeNode(4, null, null));
        new LC230().kthSmallest(root, 3);
    }

    private Integer value = null;
    private int cur = 0;

    public int kthSmallest(TreeNode root, int k) {
        midRecursion(root, k);
        return value == null ? 0 : value;
    }

    private void midRecursion(TreeNode root, int k) {
        if (root == null) {
            return;
        }

        midRecursion(root.left, k);
        cur ++;
        if (cur == k && value == null) {
            System.out.println(root.val);
            value = root.val;
            return;
        }
        midRecursion(root.right, k);
    }




}
