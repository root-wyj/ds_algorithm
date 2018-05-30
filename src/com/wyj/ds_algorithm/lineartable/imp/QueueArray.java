package com.wyj.ds_algorithm.lineartable.imp;

import java.lang.reflect.Array;

/**
 * 用有限长度的循环数组 来实现 
 * @author wuyingjie
 * @date 2018年5月30日
 */

public class QueueArray<T> {
	
	private static final int DEFAULT_MAX_SIZE = 16;
	
	private T[] array;
	private int maxSize;
	private int size;
	
	private int head;
	private int tail;
	
	public QueueArray(Class<T> type) {
		this(DEFAULT_MAX_SIZE, type);
	}
	
	@SuppressWarnings("unchecked")
	public QueueArray(int maxSize, Class<T> type) {
		array = (T[])Array.newInstance(type, maxSize);
		this.maxSize = maxSize;
		this.size = 0;
		this.head = 0;
		this.tail = 0;
	}
	
	private int realIndex(int index) {
		return (index + maxSize)%maxSize;
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void add(T data) {
		if (getSize() == maxSize) {
			throw new RuntimeException("队列满了");
		}
		array[tail] = data;
		tail = realIndex(tail - 1);
		size ++;
	}
	
	public T pop() {
		if (isEmpty()) {
			return null;
		}
		
		T ret = array[head];
		head = realIndex(head - 1);
		size -- ;
		return ret;
	}
	
	public static void main(String[] args) {
		QueueArray<String> queue = new QueueArray<>(String.class);
		
		queue.add("1");
		queue.add("2");
		queue.add("3");
		
		System.out.println(queue.pop());
		System.out.println(queue.pop());
		System.out.println(queue.pop());
		System.out.println(queue.pop());
	}

}
