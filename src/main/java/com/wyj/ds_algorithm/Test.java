package com.wyj.ds_algorithm;

/**
 * Created
 * Author: wyj
 * Email: 18346668711@163.com
 * Date: 2018/9/3
 */
public class Test {

    public static void main(String[] args) {
        String str = "a";
        String str2 = new String("a");
        String str3 = new String("a");
        System.out.println(str == str2);
        System.out.println(str3 == str2);


        int i = 1;
        Integer i2 = new Integer(1);
        System.out.println(i == i2);
    }
}
