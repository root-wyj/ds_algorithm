package com.wyj.ds_algorithm.leecode;

/**
 * 二叉树的最大路径和。
 * 输入是 [-10,9,20,null,null,15,7] -10 为根节点， 1 2 为0 的两个子节点 3 4为1的子节点，5 6为2的子节点
 * @author wuyingjie <13693653307@163.com>
 * Created on 2021-05-10
 */
public class LC124 {

    static int DEFAULT_NODE_VALUE = Integer.MIN_VALUE;

    static int max = DEFAULT_NODE_VALUE;

    public static void main(String[] args) {
        Integer[] array = {-10, 9, 20, null, null ,15, 7};
        maxPathValue(array, 0);
        System.out.println(max);
    }

    private static int maxPathValue(Integer[] tree, int nodeIndex) {

        int maxIndex = tree.length - 1;
        if (nodeIndex > maxIndex || tree[nodeIndex] == null) {
            return DEFAULT_NODE_VALUE;
        }

        int leftIndex = (nodeIndex << 1) + 1;
        int rightIndex = (nodeIndex << 1) + 2;

        int leftPathMax = maxPathValue(tree, leftIndex);
        int rightPathMax = maxPathValue(tree, rightIndex);
        int curNodeValue = getNodeValue(tree, nodeIndex);

        int retLeftMax = curNodeValue + Math.max(0, leftPathMax);
        int retRightMax = curNodeValue + Math.max(0, rightPathMax);

        int leftAndRight = curNodeValue + Math.max(0, leftPathMax) + Math.max(0, rightPathMax);
        int left = Math.max(0, curNodeValue) + leftPathMax;
        int right = Math.max(0, curNodeValue) + rightPathMax;
        max = Math.max(max, Math.max(leftAndRight, Math.max(left, right)));

        return Math.max(retLeftMax, retRightMax);
    }

    private static int getNodeValue(Integer[] tree, int index) {
        int maxIndex = tree.length - 1;
        return index > maxIndex ? DEFAULT_NODE_VALUE : tree[index] == null ? DEFAULT_NODE_VALUE : tree[index];
    }


}
