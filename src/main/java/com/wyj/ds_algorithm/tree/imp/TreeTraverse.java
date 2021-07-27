package com.wyj.ds_algorithm.tree.imp;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.wyj.ds_algorithm.TreeNode;
import com.wyj.ds_algorithm.leecode.LC108;

/**
 * 树的遍历
 * 深度 广度 见 https://developer.51cto.com/art/202004/614590.htm
 * https://blog.csdn.net/guoziqing506/article/details/51355492
 * @author wuyingjie <13693653307@163.com>
 * Created on 2021-05-10
 */
public class TreeTraverse {

    /**
     * 先序递归遍历
     */
    public void firstRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root);
        firstRecursion(root.left);
        firstRecursion(root.right);
    }

    /**
     * 中序递归遍历
     */
    public void midRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        midRecursion(root.left);
        System.out.println(root);
        midRecursion(root.right);
    }

    /**
     * 后序递归遍历
     */
    public void lastRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        lastRecursion(root.left);
        lastRecursion(root.right);
        System.out.println(root);
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 4, 5};
        LC108 lc108 = new LC108();
        TreeNode treeNode = lc108.sortedArrayToBST2(nums);
        new TreeTraverse().firstForDeep(treeNode, new Stack<>());

    }
    /**
     * 深度递归遍历
     */
    public void deepRecursion(TreeNode root) {

    }
    public void firstForDeep(TreeNode root, Stack<TreeNode> stack) {
        if (root == null) {
            return;
        }
        stack.push(root);
        if (root.val == 3 || root.val == 5) {
            System.out.println(root.val + ":");
            stack.forEach(item -> {
                System.out.print(item.val + " ");
            });
            System.out.println("\n");
        }
        firstForDeep(root.left, stack);
        firstForDeep(root.right, stack);
        stack.pop();
    }


    /**
     * 广度递归遍历
     */
    public void levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        levelRecursion(queue);
    }
    private void levelRecursion(Queue<TreeNode> queue) {
        if (queue.isEmpty()) {
            return;
        }
        TreeNode node;
        Queue<TreeNode> newQueue = new LinkedList<>();
        while ((node = queue.poll()) != null) {
            System.out.println(node.val);
            if (node.right != null) {
                newQueue.offer(node.right);
            }

            if (node.left != null) {
                newQueue.offer(node.left);
            }
        }
        levelRecursion(newQueue);
    }


}
