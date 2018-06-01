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
			
			tree = balancedTree(tree);
			
		}
		
		tree.height = Math.max(getHeight(tree.left), getHeight(tree.right)) + 1;
		
		return tree;
	}
	
	
	public void delete(T data) {
		this.mRoot = delete(mRoot, data);
	}
	
	private AVLNode<T> delete(AVLNode<T> tree, T data) {
		if (tree == null || data == null) return tree;
		
		int compared = data.compareTo(tree.data);
		if (compared < 0) {
			tree.left = delete(tree.left, data);
		} else if (compared > 0) {
			tree.right = delete(tree.right, data);
		} else {
			// 相等，这就是要删除的节点
			if (tree.left != null && tree.right != null) {
				
				//删除的时候 和之前有点不一样，如果直接交换后继节点的话，并删除叶子节点的话，
				// 而且平衡性也可能会受到了影响，可以直接掉 balance方法，保持树的平衡
				// 但是，由于直接删掉了叶子节点， 从原tree节点 到被删掉的叶子节点之间的高度 就会不正确
				// 最后 通过下面的方式 巧妙解决（不直接删除，继续交给子树去删除）。
				
				//如果直接去找 后继节点的话，可能会导致树不平衡，增加平衡时候的消耗，我们可以通过比较左右子树的高度 保证这次的删除操作 不会出现不平衡(后来发现也不一定)
				
				AVLNode<T> minNode = min(tree.right);
				tree.data = minNode.data;
				tree.right = delete(tree.right, minNode.data);
			} else if (tree.left == null && tree.right == null) {
				
				return null;
			} else {
				
				if (tree.left == null) {
					tree.data = tree.right.data;
					tree.right = null;
				}
				
				if (tree.right == null) {
					tree.data = tree.left.data;
					tree.left = null;
				}
			}
		}
		
		balancedTree(tree);
		
		
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
	private AVLNode<T> balancedTree(AVLNode<T> tree) {
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
	
	private AVLNode<T> min(AVLNode<T> tree) {
		if (tree == null) throw new IllegalArgumentException("非法参数 null");
		
		if (tree.left != null) return min(tree.left);
		else return tree;		
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
		
		tree.delete(13);
		
		System.out.println("先序遍历：");
		tree.preOrderPrint(tree.mRoot);
		System.out.println();
		
		System.out.println("中序遍历：");
		tree.midOrderPrint(tree.mRoot);
		System.out.println();
		
		
	}
	
	
}
