package com.wyj.ds_algorithm.sort;

import java.util.*;

public class MergeSort_Batch {



    static class Node {
        Integer val;
        Iterator<Integer> iterator;

        Node(Integer val, Iterator<Integer> iterator) {
            this.val = val;
            this.iterator = iterator;
        }

        @Override
        public String toString() {
            return val.toString();
        }
    }


    public static List<Integer> merge(List<List<Integer>> data) {
        // 需要知道当前 遍历的那个元素 以及 数组遍历的终点
//        int[] curIndex = new int[data.size()];
//        int[] maxIndex = new int[data.size()];

        List<Integer> ret = new ArrayList<>();

        // 这里采用iterator的方式，
        // 一是 可以避免 data是linkList，导致的效率问题，二是 可以简化逻辑，无需再 判断终点
        Iterator<List<Integer>> dataIterator = data.iterator();

        List<Node> list = new ArrayList<>();

        while (dataIterator.hasNext()) {
            Iterator<Integer> iterator = dataIterator.next().iterator();
            if (iterator.hasNext()) {
                list.add(new Node(iterator.next(), iterator));
            }
        }

        while (!list.isEmpty()) {
            heapSort(list);
            Node minNode = list.get(0);
            ret.add(minNode.val);

            if (minNode.iterator.hasNext()) {
                minNode.val = minNode.iterator.next();
            } else {
                list.remove(0);
            }
        }

        return ret;

    }

    private static void heapSort(List<Node> array) {
        for (int i=1; i<array.size(); i++) {
            int childIndex = i;
            int parentIndex = (childIndex-1) / 2;
            while (array.get(childIndex).val < array.get(parentIndex).val) {
                // swap
                Node tmp = array.get(childIndex);
                array.set(childIndex, array.get(parentIndex));
                array.set(parentIndex, tmp);

                childIndex = parentIndex;
                parentIndex = (childIndex - 1) / 2;

            }
        }
    }


    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(10, 20, 30, 50));
        list.add(Arrays.asList(1, 3, 21));
        list.add(Arrays.asList(100, 200));
        list.add(Arrays.asList(2, 7, 8, 22, 80));
        System.out.println(merge(list));
    }

}
