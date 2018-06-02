package com.wyj.ds_algorithm.tree.imp;

import com.wyj.ds_algorithm.util.TreeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树、二叉排序树(Binary Sort|Search Tree)
 * 注意： 该版本并不支持 相等的情况，相等的情况建议使用链表 串在某一个节点上
 * @author wuyingjie
 * @date 2018年5月30日
 */

public class BSTree<T extends Comparable<T>> {

	public static class BSTNode<V extends Comparable<V>> {
		public BSTNode<V> parent;
		public BSTNode<V> left;
		public BSTNode<V> right;
		public V data;
		
		BSTNode(V data, BSTNode<V> parent, BSTNode<V> left, BSTNode<V> right) {
			this.data = data;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}
	}
	
	public static interface TraverseHook<H extends Comparable<H>>{
		void hook(BSTNode<H> node);
	}
	
	public BSTNode<T> mRoot;
	
	public BSTree() {
		mRoot = null;
	}

	public BSTree(T data) {
		mRoot = new BSTNode<>(data, null, null, null);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node == null) return null;

		while(node.right != null)
			node = node.right;

		return node;
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node == null) return null;

		while(node.left != null)
			node = node.left;

		return node;
	}
	
	// 比到没朋友 方法
	public void insert(T data) {
		if (mRoot == null) {
			mRoot = new BSTNode<>(data, null, null, null);
			return;
		}
		
		BSTNode<T> parent = mRoot;
		BSTNode<T> node = mRoot;
		while(node != null) {
			int compared = data.compareTo(node.data);
			if (compared < 0) {
				parent = node;
				node = node.left;
			} else if (compared > 0) {
				parent = node;
				node = node.right;
			} else {
				break;
			}
		}
		
		if (node != null) {
			return ;
		}
		
		BSTNode<T> newNode = new BSTNode<T>(data, parent, null, null);
		
		if (newNode.data.compareTo(parent.data) > 0) {
			parent.right = newNode;
		} else {
			parent.left = newNode;
		}
		
		
//		insert(mRoot, data);
	}

	/**
	 * 该节点的前驱节点(感觉我理解的不太对。。。)
	 * <p>前驱：有序打印 该节点的前一个节点 就是它的前驱节点</p>
	 * @param node
	 * @return
	 */
	private BSTNode<T> predecessor(BSTNode<T> node) {
		// 如果存在左子树，左子树的最大的节点就是该节点的前驱节点
		if (node.left != null)
			return maximum(node.left);

		// 不存在 左子树
		// 如果该节点是右孩子 父节点就是前驱节点
		// 如果该节点是左孩子 该节点的最低父节点p1，并且该父节点p1是其父节点p2的右节点，那么p2就是前驱
		if (node.parent.right == node)
			return node.parent;

		BSTNode<T> parent = node.parent;
		if (parent != null && parent.left==node) {
			node = parent;
			parent = parent.parent;
		}
		return parent;
	}

	/**
	 * 该节点的后继节点
	 * <p>后继：有序打印 该节点的后一个节点 就是它的后继节点</p>
	 * @param node
	 * @return
	 */
	private BSTNode<T> successor(BSTNode<T> node) {
		if (node.right != null)
			return minimum(node.right);

		// 如果x没有右孩子。则x有以下两种可能：
		// (01) x是"一个左孩子"，则"x的后继结点"为 "它的父结点"。
		// (02) x是"一个右孩子"，则查找"x的最低的父结点，并且该父结点要具有左孩子"，找到的这个"最低的父结点"就是"x的后继结点"。
		BSTNode<T> parent = node.parent;
		while ((parent!=null) && (node==parent.right)) {
			node = parent;
			parent = parent.parent;
		}

		return parent;
	}

	public void delete(T data) {
		BSTNode<T> node = search(data);
		if (node == null) return;

		// child都不为空 直接删除
		if (node.left != null && node.right != null) {
			// 获取后继节点 并交换内容
			BSTNode<T> successor = successor(node);
			node.data = successor.data;

			node = successor;
		}

		BSTNode<T> child = null;

		// 交换完位置后 只剩下两种情况，只有一个节点 或者没有节点
		if (node.left != null) {
			child = node.left;
		} else {
			child = node.right;
		}

		if (child != null) {
			child.parent = node.parent;
		}

		if (node.parent.left == node) {
			node.parent.left = child;
		} else {
			node.parent.right = child;
		}

	}
	
	public BSTNode<T> search(T data) {
		return search(mRoot, data);
	}
	
	private BSTNode<T> search(BSTNode<T> node, T data) {
		int compared = data.compareTo(node.data);
		if (compared == 0) {
			return node;
		}
		
		if (compared < 0) {
			if (node.left != null) {
				return search(node.left, data);
			} else {
				return null;
			}
		}
		
		if (compared > 0) {
			if (node.right != null) {
				return search(node.right, data);
			} else {
				return null;
			}
		}
		
		return null;
	}
	
	public void traverseLeft(BSTNode<T> node, TraverseHook<T> hook) {
		if (node.left !=null) {
			traverseLeft(node.left, hook);
		}
		
		hook.hook(node);
		
		if (node.right != null) {
			traverseLeft(node.right, hook);
		}
	}

	public void traverseMid(BSTNode<T> node, TraverseHook<T> hook) {
		hook.hook(node);

		if (node.left !=null) {
			traverseMid(node.left, hook);
		}


		if (node.right != null) {
			traverseMid(node.right, hook);
		}
	}
	
	public static void main(String[] args) {

		int[] array = {50, 30, 80, 20, 35, 34, 32, 40, 70, 75, 100};

		BSTree<Integer> tree = new BSTree<Integer>();

		for (int i : array) {
			tree.insert(i);
		}

		final List<Integer> list = new ArrayList<>();
		tree.traverseLeft(tree.mRoot, new TraverseHook<Integer>() {
			@Override
			public void hook(BSTNode<Integer> node) {
				list.add(node.data);
			}
		});

		System.out.println(list);

		tree.delete(40);

		list.clear();
		tree.traverseLeft(tree.mRoot, new TraverseHook<Integer>() {
			@Override
			public void hook(BSTNode<Integer> node) {
				list.add(node.data);
			}
		});
		System.out.println(list);

		TreeUtils.print(tree);
	}
	
	

}
