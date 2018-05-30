package com.wyj.ds_algorithm.tree.imp;

/**
 * 二叉搜索树、二叉排序树(Binary Sort|Search Tree)
 * 注意： 该版本并不支持 相等的情况，相等的情况建议使用链表 串在某一个节点上
 * @author wuyingjie
 * @date 2018年5月30日
 */

public class BSTree<T extends Comparable<T>> {

	public static class BSTNode<V extends Comparable<V>> {
		BSTNode<V> parent;
		BSTNode<V> left;
		BSTNode<V> right;
		V data;
		
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
	
	private BSTNode<T> mRoot;
	
	public BSTree(T root) {
		mRoot = new BSTNode<>(root, null, null, null);
	}
	
	// 比到没朋友 方法
	public void insert(T data) {
		
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
	
	public void delete(T data) {
		
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
	
	public static void main(String[] args) {
		BSTree<Integer> tree = new BSTree<Integer>(30);
		tree.insert(10);
		tree.insert(20);
		tree.insert(5);
		tree.insert(15);
		tree.insert(25);
		
		tree.insert(50);
		tree.insert(40);
		tree.traverseLeft(tree.mRoot, new TraverseHook<Integer>() {
			@Override
			public void hook(BSTNode<Integer> node) {
				System.out.println(node.data);
			}
		});
	}
	
	

}
