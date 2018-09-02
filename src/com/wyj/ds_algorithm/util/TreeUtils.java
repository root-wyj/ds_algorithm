package com.wyj.ds_algorithm.util;

import com.wyj.ds_algorithm.tree.imp.BSTree;

/**
 * Created
 * Author: wyj
 * Email: 18346668711@163.com
 * Date: 2018/5/30
 */
public class TreeUtils {
    //------------下面代码是用于输出树的工具代码------------------------

    public static final String  PREFIX_BRANCH = "├";//树枝
    public static final String  PREFIX_TRUNK  = "│ ";//树干
    public static final String  PREFIX_LEAF   = "└";//叶子
    public static final String  PREFIX_EMP    = "  ";//空
    public static final String  PREFIX_LEFT   = "─L─";//左
    public static final String  PREFIX_RIGTH  = "─R─";//右

    private static boolean hasChild(BSTree.BSTNode node){
        return node.left != null || node.right != null;
    }

    public static void print(BSTree tree){
        if(tree != null && tree.mRoot != null){
            System.out.println(tree.mRoot.data);
            print(tree.mRoot, "");
        }
    }

    public static void print(BSTree.BSTNode node, String prefix){
        if(prefix == null){
            prefix = "";
        } else {
            prefix = prefix.replace(PREFIX_BRANCH, PREFIX_TRUNK);
            prefix = prefix.replace(PREFIX_LEAF, PREFIX_EMP);
        }
        if(hasChild(node)){
            if(node.right != null){
                System.out.println(prefix + PREFIX_BRANCH + PREFIX_RIGTH + node.right.data);
                if(hasChild(node.right)){
                    print(node.right, prefix + PREFIX_BRANCH);
                }
            } else {
                System.out.println(prefix + PREFIX_BRANCH + PREFIX_RIGTH);
            }

            if(node.left != null){
                System.out.println(prefix + PREFIX_LEAF + PREFIX_LEFT + node.left.data);
                if(hasChild(node.left)){
                    print(node.left, prefix + PREFIX_LEAF);
                }
            } else {
                System.out.println(prefix + PREFIX_LEAF + PREFIX_LEFT);
            }
        }
    }

    private int position = 0;

    public int getPosition() {
        return position++;
    }

    public static void main(String[] args) {
        TreeUtils t = new TreeUtils();
        System.out.println(t.getPosition());
        System.out.println(t.position);
    }

}
