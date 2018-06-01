package com.wyj.ds_algorithm.tree.imp;

/**
 * 平衡二叉树(AVLTree)，是二叉排序树(BSTree)的升级版，保证任何一颗子树的左右节点的高度差不超过1
 * 
 * 平衡二叉树，通过节点的高度参数来判断是否平衡，并通过旋转节点，来保证平衡
 * 
 * @author wuyingjie
 * @date 2018年6月1日
 */

public class AVLTree<T extends Comparable<T>>{
	
	public static class AVLNode<N extends Comparable<N>> {
		public AVLNode<N> left;
		public AVLNode<N> right;
		public int height;
		N data;
		
		public AVLNode(N data, AVLNode<N> left, AVLNode<N> right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
	
	
	public AVLNode<T> mRoot;
	
	public AVLTree() {
		this.mRoot = null;
	}
	
	public void insert(T data) {
		this.mRoot = insert(mRoot, data);
	}
	
	// insert 还是清楚的分情况讨论，只不过 需要在增加完节点之后，需要平衡一下，调整树的结构，
	// 同时，因为增加了节点，改变了树的结构，需要重新计算当前树的高度，通过递归，也会重新计算顶层节点的高度
	private AVLNode<T> insert(AVLNode<T> tree, T data) {
		if (tree == null) {
			tree = new AVLNode<>(data, null, null);
		} else {
			int compared = data.compareTo(tree.data);
			if (compared < 0) {
				// 如果小于 当前节点，交给左子树
				tree.left = insert(tree.left, data);
			} else if (compared > 0){
				// 如果大于 当前节点 交给右子树
				tree.right = insert(tree.right, data);
			} else {
				System.err.println("unsupprot equals value insert twice");
				return tree;
			}
			
			tree = blancedTree(tree);
			
		}
		
		tree.height = Math.max(getHeight(tree.left), getHeight(tree.right)) + 1;
		
		return tree;
	}
	
	private int getHeight(AVLNode<T> node) {
		if (node == null) return 0; 
		
		return node.height;
	}
	
	/**
	 * 通过各种旋转手段，保证二叉树的平衡，前提条件是 左右子树的高度是正确的。
	 * @param tree
	 */
	private AVLNode<T> blancedTree(AVLNode<T> tree) {
		int diff = getHeight(tree.left) - getHeight(tree.right);
		
		
		if (diff >= 2) {
			//左子树长 再判断是 LL 还是 LR
			int diff2 = getHeight(tree.left.left) - getHeight(tree.left.right);
			if (diff2 > 0) {
				// LL 旋转
				tree = rotateLL(tree);
			} else {
				// LR 旋转  不会出现相等的情况的
				tree = rotateLR(tree);
			}
		}
		
		if (diff <= -2) {
			//右子树长 
			int diff2 = getHeight(tree.right.left) - getHeight(tree.right.right);
			if (diff2 > 0) {
				tree = rotateRL(tree);
			} else {
				tree = rotateRR(tree);
			}
		}
		
		return tree;
	}
	
	// 注意啊 不是向左 旋转啊    是LL长
	private AVLNode<T> rotateLL(AVLNode<T> tree) {
		AVLNode<T> futureTree = tree.left;
		tree.left = futureTree.right;
		futureTree.right = tree;
		
		tree.height = Math.max(getHeight(tree.left), getHeight(tree.right)) + 1;
		futureTree.height = Math.max(getHeight(futureTree.left), getHeight(futureTree.right)) + 1;
		
		return futureTree;
	}
	
	private AVLNode<T> rotateLR(AVLNode<T> tree) {
		tree.left = rotateRR(tree.left);
		return rotateLL(tree);
	}
	
	private AVLNode<T> rotateRR(AVLNode<T> tree) {
		AVLNode<T> futureTree = tree.right;
		tree.right = futureTree.left;
		futureTree.left = tree;
		
		tree.height = Math.max(getHeight(tree.left), getHeight(tree.right)) + 1;
		futureTree.height = Math.max(getHeight(futureTree.left), getHeight(futureTree.right)) + 1;
		
		return futureTree;
	}
	
	private AVLNode<T> rotateRL(AVLNode<T> tree) {
		tree.right = rotateLL(tree.right);
		return rotateRR(tree);
	}
	
	public void preOrderPrint(AVLNode<T> tree) {
		if (tree == null) {
			return;
		}
		
		System.out.print("   "+tree.data);
		
		if (tree.left != null) preOrderPrint(tree.left);
		
		if (tree.right != null) preOrderPrint(tree.right);
		
	}
	
	public void midOrderPrint(AVLNode<T> tree) {
		if (tree == null) {
			return;
		}
		
		if (tree.left != null) midOrderPrint(tree.left);
		
		System.out.print("   "+tree.data);
		
		if (tree.right != null) midOrderPrint(tree.right);
		
	}
	
	
	public static void main(String[] args) {
		int[] datas = {3, 2, 1, 4, 5, 6, 7, 16, 15, 14, 13, 12, 11, 10, 8, 9};
		AVLTree<Integer> tree = new AVLTree<>();
		for (int item : datas) {
			tree.insert(item);
		}
		
		
		System.out.println("先序遍历：");
		tree.preOrderPrint(tree.mRoot);
		System.out.println();
		
		System.out.println("中序遍历：");
		tree.midOrderPrint(tree.mRoot);
		System.out.println();
		
		
	}
	
	
}
