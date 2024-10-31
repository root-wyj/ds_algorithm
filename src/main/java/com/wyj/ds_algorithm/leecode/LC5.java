package com.wyj.ds_algorithm.leecode;

/**
 * 寻找最长回文子串，中心扩散法
 * 时间复杂度O(log(m+n))
 * @author wuyingjie
 * Date: 2024/7/29
 */
public class LC5 {

    // rabbacbc
    // abcbc
    public static String longestPalindrome(String s) {
        int start = 0, end = 0;
        for (int mid=0; mid<s.length(); mid++) {
            int length1 = longth(s, mid, mid);
            int length2 = longth(s, mid, mid+1);
            int len = Math.max(length1, length2);
            if (len > end-start+1) {
                // 偶数
                start = mid - (len-1)/2;
                end = mid + len / 2;
            }
        }
        return s.substring(start, end+1);
    }

    public static int longth(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return j-i-1;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("aaa"));
    }

}
