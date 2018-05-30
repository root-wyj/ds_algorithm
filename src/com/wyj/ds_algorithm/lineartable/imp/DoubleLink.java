package com.wyj.ds_algorithm.lineartable.imp;


/**
 * Java 实现的双向循环链表
 * 
 * @author wuyingjie
 * @date 2018年5月29日
 */

public class DoubleLink<T> {

	private class Node<V> {
		
		T data;
		Node<V> pre;
		Node<V> next;
		
		public Node(T data, Node<V> pre, Node<V> next) {
			this.data = data;
			this.pre = pre;
			this.next = next;
		}
	}
	
	private Node<T> head = new Node<>(null, null, null);
	private int size;
	
	public DoubleLink () {
		head.next = head.pre = head;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public T get(int index) {
		Node<T> node = getNode(index);
		return node == null ? null : node.data;
	}
	
	private Node<T> getNode(int index) {
		if (index < 0 || index >= size) {
			return null;
		}
		
		if (index <= size / 2) {
			int curIndex = 0;
			Node<T> ret = head.next;
			while (curIndex < index) {
				ret = ret.next;
				curIndex ++;
			}
			return ret;
		} else {
			int curBackIndex = 0;
			Node<T> ret = head.pre;
			while (curBackIndex + index < size-1) {
				ret = ret.pre;
				curBackIndex ++;
			}
			return ret;
		}
		
	}
	
	public void insert(int index, T data) {
		if (index == 0) {
			Node<T> newNode = new Node<>(data, head, head.next);
			head.next.pre = newNode;
			head.next = newNode;
			size ++;
			return;
		}
		
		
		Node<T> cur = getNode(index);
		if (cur == null) throw new RuntimeException("invalid index value:"+index);
		
		Node<T> newNode = new Node<>(data, cur.pre, cur);
		
		cur.pre.next = newNode;
		cur.pre = newNode;
		size ++;
	}
	
	// 插头 或者 插尾  都应该从 header说起，可以看做是比较特殊的操作吧 
	public void insertFirst(T data) {
		insert(0, data);
	}
	
	// 插头 或者 插尾  都应该从 header说起，可以看做是比较特殊的操作吧 
	public void appendLast(T data) {
		Node<T> newNode = new Node<>(data, head.pre, head);
		head.pre.next = newNode;
		head.pre = newNode;
		size ++;
	}
	
	public T getFirst() {
		return isEmpty() ? null : head.next.data;
	}
	
	public T getLast() {
		return isEmpty() ? null : head.pre.data;
	}
	
	public T delete(int index) {
		Node<T> curNode = getNode(index);
		
		if (curNode == null) return null;
		
		T ret = curNode.data;
		curNode.pre.next = curNode.next;
		curNode.next.pre = curNode.pre;
		curNode.data = null;
		size --;
		return ret;
	}
	
	
	public static void main(String[] args) {
		DoubleLink<String> list = new DoubleLink<>();
		list.insertFirst("1");
		list.appendLast("2");
		list.insert(1, "3");
		
		System.out.println(list.getFirst());
		System.out.println(list.getLast());
		System.out.println(list.get(1));
	}
	
}
