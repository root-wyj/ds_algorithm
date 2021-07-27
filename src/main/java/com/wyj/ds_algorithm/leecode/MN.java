package com.wyj.ds_algorithm.leecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuyingjie <13693653307@163.com>
 * Created on 2021-05-11
 */
public class MN {

    public static void main(String[] args) {
        MN mn = new MN(2, 5);
        mn.suum();
    }

    Map<Integer, List<List<Integer>>> sumTable = new HashMap<>();

    int m,n;
    public MN(int m, int n) {
        this.m = m;
        this.n = n;
    }

    public void suum() {
        List<Integer> list_1 = new ArrayList<>();
        list_1.add(1);

        List<List<Integer>> lists_1 = new ArrayList<>();
        lists_1.add(list_1);
        sumTable.put(1, lists_1);

        List<List<Integer>> lists = sumMn(m, n);
        for (List<Integer> item : lists) {
            System.out.println(item);
        }
    }

    public List<List<Integer>> sumMn(int m, int n) {
        List<List<Integer>> ret = sumTable.get(n);
        if (ret != null) {
            return ret;
        }

        int mid = n / 2;
        ret = new ArrayList<>();
        for (int i=1; i<=mid; i++) {
            List<List<Integer>> left = sumMn(m, i);
            List<List<Integer>> right = sumMn(m, n - i);
            for (List<Integer> itemLeft : left) {
                for (List<Integer> itemRight : right) {
                    List<Integer> item = new ArrayList<>(itemLeft);
                    item.addAll(itemRight);
                    ret.add(item);
                }
            }
        }

        if (n <= m && n != this.n) {
            List<Integer> list_n = new ArrayList<>();
            list_n.add(n);
            ret.add(list_n);
        }

        return ret;
    }

}
