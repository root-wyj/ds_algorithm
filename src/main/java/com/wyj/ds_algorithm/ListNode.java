package com.wyj.ds_algorithm;

/**
 * @author wuyingjie <13693653307@163.com>
 * Created on 2021-05-11
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(val+"");
        ListNode tmp = next;
        while (tmp != null) {
            sb.append(",").append(tmp.val);
            tmp = tmp.next;
        }
        return sb.toString();
    }
}
