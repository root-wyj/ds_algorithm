package com.wyj.ds_algorithm.leecode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.wyj.ds_algorithm.TreeNode;

/**
 * 二叉树中 两个节点的 最深公共祖先
 * @author wuyingjie <13693653307@163.com>
 * Created on 2021-05-11
 */
public class LC236 {

    List<TreeNode> pTrace = new ArrayList<>();
    List<TreeNode> qTrace = new ArrayList<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        firstMid(root, new Stack<>(), p ,q);
        int i=0;
        while (i < pTrace.size() && i < qTrace.size()) {
            if (pTrace.get(i).val == qTrace.get(i).val) {
                i++;
            } else {
                break;
            }
        }

        if (i > 0) {
            return pTrace.get(i-1);
        } else {
            return root;
        }


    }

    private void firstMid(TreeNode root, Stack<TreeNode> stack, TreeNode p, TreeNode q) {
        if (root == null) {
            return;
        }

        stack.push(root);
        if (root.val == p.val) {
            pTrace.addAll(stack);
        }
        if (root.val == q.val) {
            qTrace.addAll(stack);
        }
        firstMid(root.left, stack, p, q);
        firstMid(root.right, stack, p, q);
        stack.pop();

    }







}
