package com.wyj.ds_algorithm.sort;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Created
 * Author: wyj
 * Date: 2019/12/4
 */
public class SortTest {
    static int[] array1 = {1, 3, 9, 2, 5, 0, 7, 8, 6, 4};
    static int[] array2 = {1, 3, 9, 2, 5, 0, 7, 8, 6, 4, 13, 39, 2, 55, 0, 7, 98, 6, 24};
    static int[] array3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 45, 102};
    static int[] array4 = {9, 8, 7, 6, 5, 4, 3, 2, 1};

    static int[] array11 = {11, 10, 30, 44};

    public static void main(String[] args) {
//        qSortTest();
//        qSort_FillTest();
//        qSort_SingleForwardScanTest();
//        insertSort_SimpleTest();
//        insertSort_ShellTest();
//        insertSort_BisectTest();
//        swapSort_CocktailTest();
//        mergeSort_BasicTest();
//        countSort_BasicTest();
        countSort_RadixTest();
    }

    private static void testSub(int[] array, Consumer<int[]> consumer) {
        int[] tmp1 = Arrays.copyOf(array, array.length);
        consumer.accept(tmp1);
        System.out.println(Arrays.toString(tmp1));
    }

    private static void testList(Consumer<int[]> consumer) {
        testSub(array1, consumer);
        testSub(array2, consumer);
        testSub(array3, consumer);
        testSub(array4, consumer);
    }

    private static void qSortTest() {
        System.out.println("qSort");
        testSub(array11, QSort::qSort);

        testList(QSort::qSort);
    }

    private static void qSort_FillTest() {
        System.out.println("qSort_Fill");
        testSub(array11, QSort_Fill::qSort_Fill);

        testList(QSort_Fill::qSort_Fill);
    }

    private static void qSort_SingleForwardScanTest() {
        System.out.println("qSort_SingleForwardScan");
        testList(QSort_SingleForwardScan::qSort_SingleForwardScan);
    }

    private static void insertSort_SimpleTest() {
        System.out.println("insertSort_SimpleTest");
        testList(InsertSort_Simple::insertSort_Simple);
    }

    private static void insertSort_ShellTest() {
        System.out.println("insertSort_ShellTest");
        testList(InsertSort_Shell::insertSort_Shell);
    }

    private static void insertSort_BisectTest() {
        System.out.println("insertSort_BisectTest");
        testList(InsertSort_Bisect::insertSort_Bisect);
    }

    private static void swapSort_CocktailTest() {
        System.out.println("swapSort_Cocktail");
        testList(SwapSort_Cocktail::swapSort_Cocktail);
    }

    private static void mergeSort_BasicTest() {
        System.out.println("mergeSort_Basic");
        testList(MergeSort_Basic::mergeSort_Basic);
    }

    private static void countSort_BasicTest() {
        System.out.println("countSort_Basic");
        testList(CountSort_Basic::countSort_Basic);
    }

    private static void countSort_RadixTest() {
        System.out.println("CountSort_Radix");
        testList(CountSort_Radix::countSort_Radix);
    }







}
