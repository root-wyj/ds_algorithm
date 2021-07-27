package com.wyj.ds_algorithm.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU cache
 * @author wuyingjie
 * Created on 2021-05-08
 */
public class LC146 {

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 0); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.get(1);    // 返回 -1 (未找到)
        lRUCache.get(3);    // 返回 3
        lRUCache.get(4);    // 返回 4

    }

    private static class Node<T> {
        Node<T> pre;
        Node<T> next;
        T key;
        T value;

        Node(Node<T> pre, Node<T> next, T key, T value) {
            this.pre = pre;
            this.next = next;
            this.key = key;
            this.value = value;
        }
    }

    public static class LRUCache {
        private int capacity;
        private Map<Integer, Node<Integer>> dataMap;
        private Node<Integer> head;
        private Node<Integer> tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            head = new Node<>(null, null, null, null);
            tail = new Node<>(head, null, null, null);
            head.next = tail;
            dataMap = new HashMap<>();
        }

        int get(Integer key) {
            Node<Integer> node = dataMap.get(key);
            if (node == null) {
                return -1;
            } else {
                moveToTail(node);
                return node.value;
            }
        }

        private void moveToTail(Node<Integer> node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;

            node.pre = tail.pre;
            tail.pre.next = node;
            node.next = tail;
            tail.pre = node;
        }

        private void addToTail(Node<Integer> node) {
            node.pre = tail.pre;
            tail.pre.next = node;
            node.next = tail;
            tail.pre = node;
        }

        void put(int key, int value) {
            Node<Integer> existNode = dataMap.get(key);
            if (existNode != null) {
                existNode.value = value;
                moveToTail(existNode);
            } else {
                existNode = new Node<>(null, null, key, value);
                addToTail(existNode);
                dataMap.put(key, existNode);
                if (dataMap.size() > capacity) {
                    removeHead();
                }
            }
        }

        private void removeHead() {
            Node<Integer> nowHead = head.next;
            head.next = nowHead.next;
            nowHead.next.pre = head;
            nowHead.next = null;
            nowHead.pre = null;
            dataMap.remove(nowHead.key);
        }





    }

}
