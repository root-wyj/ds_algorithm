package com.wyj.ds_algorithm.leecode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 找零钱。
 * 给定一组零钱，给定一个钱数，返回能兑换的 最少零钱张数
 * 比如
 * @author wuyingjie
 * Created on 2021-05-08
 */
public class LC322 {



    Map<Integer, Integer> minTable = new HashMap<>();
    static Integer INVALID = Integer.MAX_VALUE;

    private int fun(int[] arr, int amount) {
        if (amount == 0) {
            return 0;
        }
        Integer count = count(arr, amount);
        if (count == INVALID) {
            return -1;
        } else {
            return count;
        }
    }

    private Integer count(int[] arr, int amount) {

        Integer integer = minTable.get(amount);
        if (integer != null) {
            return integer;
        } else {
            integer = INVALID;
            for (int i=0; i<arr.length; i++) {
                if (amount == arr[i]) {
                    integer = 1;
                } else if (amount > arr[i]) {
                    Integer count = count(arr, amount - arr[i]);
                    if (count != INVALID) {
                        integer = Math.min(count + 1, integer);
                    }
                }
            }
        }
        minTable.put(amount, integer);
        return integer;
    }

    public static void main(String[] args) {
        LC322 lc322 = new LC322();
        int arr[] = {186,419,83,408};
        System.out.println(lc322.fun(arr, 6249));
    }

    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

}
