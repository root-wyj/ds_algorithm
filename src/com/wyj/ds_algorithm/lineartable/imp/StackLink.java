package com.wyj.ds_algorithm.lineartable.imp;

/**
 * 
 * @author wuyingjie
 * @date 2018年5月30日
 */

public class StackLink<T> {

	private class Node<D> {
		D data;
		Node<D> pre;
		
		Node(D data, Node<D> pre) {
			this.data = data;
			this.pre = pre;
		}
	}
	
	private Node<T> top;
	private int size;
	
	public StackLink() {
		top = new Node<>(null, null);
		size = 0;
	}
	
	public void push(T data) {
		Node<T> newNode = new Node<>(data, top.pre);
		top.pre = newNode;
		size ++;
	}
	
	public T pop() {
		if (top.pre == null) {
			return null;
		}
		
		Node<T> curNode = top.pre;
		top.pre = curNode.pre;
		size --;
		return curNode.data;
	}
	
	public T peek() {
		return top.pre == null ? null : top.pre.data;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public static void main(String[] args) {
		StackLink<String> stack = new StackLink<>();
		
		stack.push("1");
		stack.push("2");
		stack.push("3");
		
		System.out.println(stack.pop());
		System.out.println(stack.peek());
	}
}
