package com.wyj.ds_algorithm.leecode;

import com.wyj.ds_algorithm.TreeNode;

import java.util.ArrayList;
import java.util.List;


/**
 * 二叉树的层序遍历
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
 */
public class LC102 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<TreeNode> rootNode = new ArrayList<>();
        if (root != null) {
            rootNode.add(root);
        }
        List<List<Integer>> data = new ArrayList<>();
        levelOrder(rootNode, data);
        return data;
    }

    public void levelOrder(List<TreeNode> rootNode, List<List<Integer>> data) {
        if (rootNode == null || rootNode.size() == 0) {
            return;
        }

        List<TreeNode> newRootNode = new ArrayList<>();
        List<Integer> value = new ArrayList<>();
        for (TreeNode node : rootNode) {
            value.add(node.val);
            if (node.left != null) {
                newRootNode.add(node.left);
            }
            if (node.right != null) {
                newRootNode.add(node.right);
            }
        }
        data.add(value);
        levelOrder(newRootNode, data);
    }

}
