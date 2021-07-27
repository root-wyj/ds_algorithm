package com.wyj.ds_algorithm.leecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.wyj.ds_algorithm.TreeNode;

/**
 * 打印树的右视图
 * @author wuyingjie <13693653307@163.com>
 * Created on 2021-05-10
 */
public class LC199 {

    private List<Integer> treeRightView(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> ret = new ArrayList<>();
        levelOrder(queue, ret);
        return ret;
    }

    private void levelOrder(Queue<TreeNode> queue, List<Integer> ret) {
        if (queue.isEmpty()) {
            return;
        }
        TreeNode node;
        Queue<TreeNode> newQueue = new LinkedList<>();
        ret.add(queue.peek().val);
        while ((node = queue.poll()) != null) {
            System.out.println(node.val);
            if (node.right != null) {
                newQueue.offer(node.right);
            }

            if (node.left != null) {
                newQueue.offer(node.left);
            }
        }
        levelOrder(newQueue, ret);
    }






}
