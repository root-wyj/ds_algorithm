package com.wyj.ds_algorithm.leecode;

import com.wyj.ds_algorithm.TreeNode;

import java.util.Stack;

/**
 * 二叉搜索树中的 第k小元素
 *
 * @author wuyingjie <13693653307@163.com>
 * Created on 2021-05-11
 */
public class LC230 {

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(30, new TreeNode(10, null, new TreeNode(20, new TreeNode(15, new TreeNode(13, null, null), new TreeNode(17, null, null)), null)), new TreeNode(40, null, null));
        TreeNode root = new TreeNode(30, null, new TreeNode(40, null, null));
        System.out.println(new LC230().kthSmallest2(root, 5));
    }

    private Integer value = null;
    private int cur = 0;

    /**
     * 中序递归遍历
     * @param root
     * @param k
     * @return
     */
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

    /**
     * 循环遍历 用栈
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest2(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode tmp = root;
        int i=0;

        while (!stack.isEmpty() || tmp != null) {

            // tmp 作为新一轮 遍历的开始rootNode，
            // 先把 所有的左子节点都塞到队列中
            while (tmp != null) {
                stack.push(tmp);
                tmp = tmp.left;
            }


            // 开始遍历 根节点(也就是这时候这个节点 已经没有左子节点了)
            // 一旦遍历了 根节点，就需要将 根节点吐出
            if (!stack.isEmpty()) {
                tmp = stack.pop();
                i++;

                if (i==k) {
                    return tmp.val;
                }

                // 根节点遍历完了，该看右节点了
                tmp = tmp.right;
            }
        }
        return 0;
    }



}
