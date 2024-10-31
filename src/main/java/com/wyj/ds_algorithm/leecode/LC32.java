package com.wyj.ds_algorithm.leecode;

import java.util.Stack;

/**
 * 最长有效括号
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 */
public class LC32 {

    private static final char start = '(';
    private static final char end = ')';

    public static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxLength = 0;
        for (int i=0; i<s.length(); i++) {
            Integer peek = stack.peek();
            if (peek < 0) {
                stack.push(i);
            } else {
                if (s.charAt(peek) == start && s.charAt(i) == end) {
                    stack.pop();
                    maxLength = Math.max(i - stack.peek(), maxLength);
                } else {
                    stack.push(i);
                }
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses(")()())"));
    }
}
