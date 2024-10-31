package com.wyj.ds_algorithm.leecode;

/**
 * k个节点一组 翻转链表
 * 这种涉及到动 头节点的，都可以 增加一个 虚拟头节点
 * @author wuyingjie
 * Created on 2021-05-07
 */
public class LC25 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        main25(2, head);
    }

    private static ListNode main25(int k, ListNode head) {
        ListNode start = head, end = head, preEnd = null, nextHead = null;
        ListNode ret = head;

        while (true) {
            int i = 1;
            while (i < k && end.next != null) {
                end = end.next;
                i++;
            }

            if (i < k) {
                return ret;
            } else {
                // 说明第一次 复制为 头节点
                if (nextHead == null) {
                    ret = end;
                }

                boolean ifRet = end.next == null;

                if (preEnd != null) {
                    preEnd.next = end;
                }

                nextHead = end.next;
                reverse(nextHead, start, k);
                preEnd = start;

                start = nextHead;
                end = nextHead;
                if (ifRet) {
                    return ret;
                }
            }

        }
    }

    private static void reverse(ListNode originPre, ListNode originHead, int k) {
        ListNode pre = originPre, head = originHead;
        ListNode next = null;
        for (int i=0; i<k; i++) {
            next = head.next;
            head.next = pre;

            pre = head;
            head = next;
        }
    }



    private static class ListNode {
        ListNode next;
        int data;
        ListNode(int data, ListNode next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return data + "";
        }
    }

    public static void reverseKNode(ListNode head, int k) {
        ListNode preList = null;
        ListNode nextList = head;

        int i=1;
        ListNode next = head;
        ListNode tmpHead = head;
        while (next.next != null) {
            next = next.next;
            i++;

            if (i == k) {

            }
            tmpHead = tmpHead.next;
        }

    }

    private static void reverse2(ListNode preList, ListNode nextList, ListNode head, int cnt) {
        ListNode next = head.next;
        for (int i=0; i<cnt; i++) {
            next = next.next;

            head.next.next = head;
            head.next = null;

        }
    }

    /**
     * 简单的链表 逆序，思路是 假设head前面有个 空节点pre，然后从head开始 一个一个的调整指针，第一个调整的就是head的指针
     */
    public ListNode simpleReverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;

            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
