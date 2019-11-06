package com.wyj.ds_algorithm.tree.imp;


/**
 * 伸展树 SplayTree。是二叉查找树，另外，当某个节点被访问时，伸展树会通过旋转
 *  将该节点变为根节点，方便下次访问。
 *
 * 关键就在于 怎么把一个很远的节点变成根节点？
 *  根据之前学习AVLTree中的各种旋转，是可以调整节点位置，将一个孩子节点变成
 *  根节点的，所以当我们找到这个节点的时候，这个节点所在的树肯定是根节点，那么
 *  再返回到上一层，肯定不是根节点了，再通过旋转变成根节点，如此递归，最后将该
 *  节点变成整个树的根节点。
 *
 *
 * Created
 * Author: wyj
 * Email: 18346668711@163.com
 * Date: 2018/6/2
 */
public class SplayTree<T extends Comparable<T>>{

    public static class SplayTreeNode<N extends Comparable<N>> {
        public N data;
        public SplayTreeNode<N> left;
        public SplayTreeNode<N> right;

        public SplayTreeNode(N data, SplayTreeNode<N> left, SplayTreeNode<N> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

    }

    public SplayTreeNode<T> mRoot;


    public SplayTreeNode<T> search(T data) {
        // 增加判断是否找到了
        if (mRoot == null || mRoot.data.compareTo(data) == 0) {
            return mRoot;
        } else {
            mRoot = this.splaySearch(mRoot, data);
            return mRoot.data.compareTo(data) == 0 ? mRoot : null;
        }
    }

    /**
     * 如果找到了某个节点，则旋转tree，将该节点变为根节点，
     * 如果没找到，原样返回
     * @param tree
     * @param data
     * @return
     */
    private SplayTreeNode<T> splaySearch(SplayTreeNode<T> tree, T data) {
        if (tree == null) return null;

        int compared = data.compareTo(tree.data);

        if (compared < 0) {
            tree.left = splaySearch(tree.left, data);
            // 如果找到了
            if (tree.left != null) {
                tree = rotateLL(tree);
            }
            return tree;
        } else if (compared > 0) {
            tree.right = splaySearch(tree.right, data);
            // 如果找到了
            if (tree.right != null) {
                tree = rotateRR(tree);
            }
            return tree;
        } else {
            return tree;
        }

    }

    /**
     * 如果左节点不为null，将左节点旋转为根节点 返回
     * @param tree
     * @return
     */
    private SplayTreeNode<T> rotateLL(SplayTreeNode<T> tree) {
        if (tree.left == null) return tree;

        SplayTreeNode<T> futureTree = tree.left;
        tree.left = futureTree.right;
        futureTree.right = tree;

        return futureTree;
    }


    /**
     * 如果右节点不为null，将右节点旋转为根节点 返回
     * @param tree
     * @return
     */
    private SplayTreeNode<T> rotateRR(SplayTreeNode<T> tree) {
        if (tree.right == null) return tree;

        SplayTreeNode<T> futureTree = tree.right;
        tree.right = futureTree.left;
        futureTree.left = tree;

        return futureTree;
    }

    /**
     * 添加 和 删除 都是按照正常的 二叉排序树的流程走的
     * 添加 添加到叶子节点
     *
     * 删除 删除掉叶子节点
     * @param data
     */
    public void insert(T data) {
        mRoot = insert(mRoot, data);
    }

    private SplayTreeNode<T> insert(SplayTreeNode<T> tree, T data) {
        if (tree == null) {
            return new SplayTreeNode<>(data, null, null);
        }

        int compared = data.compareTo(tree.data);
        if (compared < 0) {
            tree.left = insert(tree.left, data);
        } else if (compared > 0) {
            tree.right = insert(tree.right, data);
        } else {
            throw new RuntimeException("该节点已存在！！");
        }
        return tree;
    }

    public void delete(T data) {
        mRoot = delete(mRoot, data);
    }

    private SplayTreeNode<T> delete(SplayTreeNode<T> tree, T data) {
        if (tree == null) return tree;

        int compared = data.compareTo(tree.data);
        if (compared < 0) {
            tree.left = delete(tree.left, data);
        } else if (compared > 0) {
            tree.right = delete(tree.right, data);
        } else {
            if (tree.left != null && tree.right != null) {
                // 先找出叶子节点的替死鬼
                SplayTreeNode<T> targetNode = minimum(tree.right);
                // 保存替死鬼的值
                tree.data = targetNode.data;

                //删除替死鬼
                tree.right = delete(tree.right, targetNode.data);
            }
        }

        return tree;
    }

    private SplayTreeNode<T> minimum(SplayTreeNode<T> tree) {
        if (tree == null) throw new IllegalArgumentException("tree 不可以为null");

        SplayTreeNode<T> parent = tree;
        tree = tree.left;
        while (tree != null) {
            parent = tree;
            tree = tree.left;
        }

        return parent;
    }

    public void preOrderPrint(SplayTreeNode<T> tree) {
        if (tree == null) {
            return;
        }

        System.out.print("   "+tree.data);

        if (tree.left != null) preOrderPrint(tree.left);

        if (tree.right != null) preOrderPrint(tree.right);

    }

    public void midOrderPrint(SplayTreeNode<T> tree) {
        if (tree == null) {
            return;
        }

        if (tree.left != null) midOrderPrint(tree.left);

        System.out.print("   "+tree.data);

        if (tree.right != null) midOrderPrint(tree.right);

    }


    public static void main(String[] args) {
        int[] datas = {10, 50, 40, 30, 20, 60};
        SplayTree<Integer> tree = new SplayTree<>();
        for (int item : datas) {
            tree.insert(item);
        }

        tree.preOrderPrint(tree.mRoot);
        System.out.println();
        tree.midOrderPrint(tree.mRoot);
        System.out.println();

        tree.search(30);

        tree.preOrderPrint(tree.mRoot);
        System.out.println();
        tree.midOrderPrint(tree.mRoot);
        System.out.println();

        tree.search(60);

        tree.preOrderPrint(tree.mRoot);
        System.out.println();
        tree.midOrderPrint(tree.mRoot);
        System.out.println();
    }


}
