package com.wyj.ds_algorithm.leecode;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 找出字符串中 不含重复字符的最长字串的长度
 *
 * @author wuyingjie
 * Created on 2021-05-08
 */
public class LC3 {

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = now.minusMinutes(10);
        Duration between = Duration.between(now.toLocalDate(), localDateTime.toLocalDate());
        long days1 = between.toDays();
//        long days2 = Duration.between(localDateTime.toLocalDate(), now.toLocalDate()).toDays();
        System.out.println("  " + days1);
    }

    /**
     * 找以第一个字符开始的不重复的最长字串，找以第二个字符开始的不重复的最长字串。。。。 最后能找到最长的。
     */
    private static int unrepeatMaxLength(String str) {
        if (str.length() == 0) {
            return 0;
        }

        int maxLength = 1;
        char[] chars = str.toCharArray();
        for (int i=0; i<chars.length-1; i++) {
            Set<Character> set = new HashSet<>();
            set.add(chars[i]);
            int j=i+1;
            while (j < chars.length) {
                if (!set.contains(chars[j])) {
                    set.add(chars[j]);
                    maxLength = Math.max(maxLength, j-i+1);
                    j++;
                } else {
                    break;
                }
            }
        }
        return maxLength;

    }

    // 滑动窗口
    // 处理好，包含和不包含这两种逻辑 就ok了。
    // 1.入口 2.出口 3.逻辑分支
    private static int slipWindow(String str) {
        // 记录某个重复字符出现之后，滑动窗口应该开始的位置
        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        for (int i=0, j=0; j<str.length(); j++) {
            if (map.containsKey(str.charAt(j))) {
                i = Math.max(i, map.get(str.charAt(j)));
            }
            map.put(str.charAt(j), j+1);
            maxLength = Math.max(maxLength, j-i+1);
        }

        return maxLength;
    }

}
