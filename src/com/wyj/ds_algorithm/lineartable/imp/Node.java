package com.wyj.ds_algorithm.lineartable.imp;

/**
 * 
 * @author wuyingjie
 * @date 2018年5月29日
 */

public class Node<T> {
	
	private T data;
	
	private Node<T> pre;
	private Node<T> next;
	
	
	public Node(T data) {
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public Node<T> getNext() {
		return next;
	}
	
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	public Node<T> getPre() {
		return pre;
	}
	
	public void setPre(Node<T> pre) {
		this.pre = pre;
	}
	
}
