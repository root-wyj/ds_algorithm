package com.wyj.ds_algorithm.tree.imp;

import com.wyj.ds_algorithm.TreeNode;
import com.wyj.ds_algorithm.leecode.LC108;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 树的遍历
 * 深度 广度 见 https://developer.51cto.com/art/202004/614590.htm
 * https://blog.csdn.net/guoziqing506/article/details/51355492
 * @author wuyingjie <13693653307@163.com>
 * Created on 2021-05-10
 */
public class TreeTraverseTest {

    /**
     * 先序递归遍历
     */
    public void firstRecursion(TreeNode root, Stack<TreeNode> stack) {
        if (root == null) {
            return;
        }
        System.out.println(root);
        stack.push(root);
        firstRecursion(root.left, stack);
        firstRecursion(root.right, stack);
        stack.pop();
    }

    /**
     * 中序递归遍历
     */
    public void midRecursion(TreeNode root, Stack<TreeNode> stack) {
        if (root == null) {
            return;
        }
        stack.push(root);
        midRecursion(root.left, stack);
        System.out.println(root);
        midRecursion(root.right, stack);
        stack.pop();
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

    public static void main2(String[] args) {
        int[] nums = {0, 1, 2, 3, 4, 5};
        LC108 lc108 = new LC108();
        TreeNode treeNode = lc108.sortedArrayToBST2(nums);
        new TreeTraverseTest().midRecursion(treeNode, new Stack<>());

    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor(new ThreadFactory() {
            Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    System.out.println("get exception, t:" + t.getId());
                }
            };

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setUncaughtExceptionHandler(handler);
                System.out.println("create thread t:" + t.getId());
                return t;
            }
        });
        executor.execute(() -> {
            throw new RuntimeException("my error");
        });

        executor.execute(() -> {
            throw new RuntimeException("my error");
        });
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
