package com.wyj.ds_algorithm.leecode;

import com.wyj.ds_algorithm.ListNode;

/**
 * 排序链表，随便给一个无序的连标，按照升序返回
 */
public class LC148 {

    /**
     * 冒泡排序的方式解决，先把 最大的移到最上面
     */
    public static ListNode sortMaoPaoList(ListNode head) {
        ListNode sortedNode = null;
        ListNode headPre = new ListNode(0, head);

        while (sortedNode != headPre.next) {
            ListNode cur = headPre.next;
            ListNode pre = headPre;
            while (cur.next != null && cur != sortedNode) {
                if (cur.val > cur.next.val) {
                    ListNode next = cur.next.next;
                    cur.next.next = cur;
                    pre.next = cur.next;
                    cur.next = next;

                    pre = pre.next;
                    // cur 往后移了，不用动
                } else {
                    cur = cur.next;
                    pre = pre.next;
                }
            }
            if (cur == sortedNode) {
                // 提前结束排序。因为后面的都有序了
                sortedNode = pre;
            } else {
                // 第一次循环 走到了链表最后
                sortedNode = cur;
            }
        }
        return headPre.next;
    }

    public static void main(String[] args) {
        //[4,2,1,3]
        System.out.println(mergeSortList(new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))))));
//        System.out.println(mergeSortList(new ListNode(-1, new ListNode(5, new ListNode(3, new ListNode(4, new ListNode(0)))))));
//        System.out.println(mergeSortList(new ListNode(3, new ListNode(2, new ListNode(4)))));

    }

    /**
     * 归并排序的方式 排序链表
     */
    public static ListNode mergeSortList(ListNode head) {
        // 一个节点 直接返回
        if (head == null || head.next == null) {
            return head;
        }

        // 2个节点，比较后 返回
        if (head.next.next == null) {
            ListNode tail = head.next;
            if (head.val > tail.val) {
                tail.next = head;
                head.next = null;
                return tail;
            } else {
                return head;
            }
        }

        // 使用双指针方法 找到mid
        ListNode singleStepNode = head;
        ListNode doubleStepNode = head;

        while (doubleStepNode != null && doubleStepNode.next != null) {
            singleStepNode = singleStepNode.next;
            doubleStepNode = doubleStepNode.next.next;
        }

        // 上面判断了 链表长度为 1和2的情况
        // 到这里 至少3个，也就不存在 mid = head or tail 的情况了
        ListNode list1TailNode = singleStepNode;
        ListNode list2HeadNode = singleStepNode.next;

        //断开为 两个链表
        list1TailNode.next = null;

        ListNode list1 = mergeSortList(head);
        ListNode list2 = mergeSortList(list2HeadNode);

        return merge(list1, list2);

    }

    private static ListNode merge(ListNode list1, ListNode list2) {
        ListNode headPre = new ListNode();
        ListNode pre = headPre;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                pre.next = list1;
                list1 = list1.next;
            } else {
                pre.next = list2;
                list2 = list2.next;
            }
            pre = pre.next;
        }

        if (list1 != null) {
            pre.next = list1;
        }

        if (list2 != null) {
            pre.next = list2;
        }
        return headPre.next;
    }
}
