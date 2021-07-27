package com.wyj.ds_algorithm.leecode;

import com.wyj.ds_algorithm.ListNode;

/**
 * 删除链表倒数第N个元素
 * @author wuyingjie <13693653307@163.com>
 * Created on 2021-06-29
 */
public class TestNListNode {

    public static ListNode fun(ListNode head, int n) {

        ListNode newHead = new ListNode();
        newHead.next = head;
        ListNode cur = newHead;
        // 删除第N个节点 就要找到倒数第N+1的节点
        // 增加head 并且走到 倒数n个节点，就是 实际上的倒数第N+1个节点

        // 将节点 走n步
        for (int i=0; i<n; i++) {
            cur = cur.next;
        }

        ListNode nodeN = newHead;
        while (cur.next != null) {
            cur = cur.next;
            nodeN = nodeN.next;
        }

        nodeN.next = nodeN.next.next;
        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));

        fun(head, 2);


    }



}
