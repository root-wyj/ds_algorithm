package com.wyj.ds_algorithm.leecode.normal;

import com.wyj.ds_algorithm.ListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 链表-两数相加
 * @author wuyingjie
 * Date: 2024/7/29
 */
public class LC2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int senior = 0;
        ListNode num1 = l1;
        ListNode num2 = l2;
        ListNode result = null;
        ListNode head = null;
        while (num1 != null || num2 != null || senior != 0) {
            int tmp = (num1 == null ? 0 : num1.val) + (num2 == null ? 0 : num2.val) + senior;
            senior = tmp / 10;
            if (result == null) {
                result = new ListNode(tmp % 10);
                head = result;
            } else {
                result.next = new ListNode(tmp % 10);
                result = result.next;
            }
            if (num1 != null) {
                num1 = num1.next;
            }
            if (num2 != null) {
                num2 = num2.next;
            }
        }
        return head;
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        char[] chars = s.toCharArray();
        int maxLength = 0;
        for (int i=0; i<chars.length; i++) {
            Set<Character> set = new HashSet<>();
            set.add(chars[i]);
            for (int j=i+1; j<chars.length; j++) {
                if (set.contains(chars[j])) {
                    break;
                }
                set.add(chars[j]);
            }
            maxLength = Math.max(maxLength, set.size());
        }
        return maxLength;
    }

    public static int lengthOfLongestSubstring2(String s){
        if (s.length() <= 1) {
            return s.length();
        }
        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        for (int i=0, j=0; j<s.length(); j++) {
            Integer latestPos = map.get(s.charAt(j));
            if (latestPos != null) {
                i = Math.max(i,latestPos + 1);
            }
            map.put(s.charAt(j), j);
            maxLength = Math.max(maxLength, j-i+1);
        }
        return maxLength;

    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring2("abba"));
    }


}
