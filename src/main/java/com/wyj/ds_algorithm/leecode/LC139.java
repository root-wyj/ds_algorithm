package com.wyj.ds_algorithm.leecode;

import java.util.Arrays;
import java.util.List;

/**
 * 单词拆分
 * 比如给 ["lee", "code"] 和 "leeCode" 则返回true
 * 给 s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"] 返回 false
 * @author wuyingjie <13693653307@163.com>
 * Created on 2021-05-11
 */
public class LC139 {

    public static void main(String[] args) {
        LC139 lc139 = new LC139();
        System.out.println(lc139.wordBreak("leetcode", Arrays.asList("leet", "code")));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0) {
            return false;
        }

        int[] ret = new int[s.length()];
        Arrays.fill(ret, -1);
        dp(s, 0, wordDict, ret);
        return ret[0] == 1;
    }


    // 动态规划+表记忆
    private int dp(String s, int start, List<String> wordDict, int[] ret) {
        if (ret[start] >= 0) {
            return ret[start];
        }
        int length = s.length();

        int curRet = 0;
        for (String item : wordDict) {
            if (start + item.length() == length) {
                curRet = s.substring(start, length).equals(item) ? 1 : 0;
            } else if (start + item.length() < length && s.substring(start, start + item.length()).equals(item)){
                curRet =  dp(s, start + item.length(), wordDict, ret);
            } else {
                curRet = 0;
            }

            if (curRet > 0) {
                break;
            }
        }
        ret[start] = curRet > 0 ? 1 : 0;
        return curRet;
    }





}
