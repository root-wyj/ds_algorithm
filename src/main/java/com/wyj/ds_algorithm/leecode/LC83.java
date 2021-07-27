package com.wyj.ds_algorithm.leecode;

import com.wyj.ds_algorithm.ListNode;

/**
 * 删除有序链表的重复元素
 * @author wuyingjie <13693653307@163.com>
 * Created on 2021-05-11
 */
public class LC83 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (pre.val == cur.val) {
                pre.next = cur.next;
                cur = cur.next;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return head;
    }
}
