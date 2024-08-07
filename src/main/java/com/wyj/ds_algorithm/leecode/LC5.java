package com.wyj.ds_algorithm.leecode;

/**
 * 寻找最长回文子串
 * 时间复杂度O(log(m+n))
 * @author wuyingjie
 * Date: 2024/7/29
 */
public class LC5 {

    // rabbacbc
    // abcbc
    public static String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int start = 0, end = 0;
        int maxLength = 0;
        for (int i=0; i<chars.length-1; i++) {
            int startIndex = i, endIndex = i;
            if (chars[i] == chars[i+1]) {
                startIndex = i-1;
                endIndex = i+2;
            } else {
                startIndex = i-1;
                endIndex = i + 1;
            }

            while(startIndex >= 0 && endIndex <chars.length && chars[startIndex] == chars[endIndex]) {
                startIndex--;
                endIndex++;
            }
            int curLength = endIndex - startIndex - 2;
            if (curLength > maxLength) {
                maxLength = curLength;
                start = startIndex + 1;
                end = endIndex - 1;
            }
        }
        return s.substring(start, end + 1);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("aaa"));
    }

}
