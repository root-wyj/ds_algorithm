package com.wyj.ds_algorithm.tree.imp;

import java.util.Random;

/**
 * 跳表 https://www.cnblogs.com/fasionchan/p/7052622.html
 *
 * Created
 * Author: wyj
 * Date: 2019/11/8
 */
public class JumpTable<T extends Comparable<T>> {

    private static final int MAX_LEVEL = 16;

    private static final  Node MAX_NODE = null;

    private Node<T> head = new Node<>(MAX_LEVEL, null);
    private int maxLevel;

    private int size = 0;

    private Random random = new Random();

    private int randomLevel() {
        int i = 1;
        while ((random.nextInt() & 1) == 1 && i < MAX_LEVEL-1) {
            i ++;
        }
        return i;
    }

    public boolean add(T data) {

        if (size == 0) {
            Node<T> newNode = new Node<>(randomLevel(), data);
            for (int i=0; i< newNode.level; i++) {
                head.next[i] = newNode;
            }
            size++;
            maxLevel = newNode.level;
            return true;
        }

        Node<T> foundNode = find(data);

        if (foundNode.data != null) {
            return true;
        }

        Node<T> newNode = new Node<>(randomLevel(), data);

        // foundNode.level == maxLevel is always true
        if (newNode.level > foundNode.level) {
            for (int i=newNode.level-1; i>=foundNode.level; i--) {
                head.next[i] = newNode;
            }

            System.out.println("level update, before:"+maxLevel+", after:"+newNode.level+", data:"+newNode.data);
            maxLevel = newNode.level;
        }

        int minLevel = Math.min(newNode.level, foundNode.level);
        for (int i = 0; i< minLevel; i++) {
            newNode.next[i] = foundNode.next[i].next[i];
            foundNode.next[i].next[i] = newNode;
        }

        size++;

        return true;

    }

    public boolean delete(T data) {
        // 在delete 情况下，找到的都是它的前驱，
        // 如果有一个node 的下一个就是 data，那么说明 找到了这个t
        // 那么就 搞一下指针
        Node<T> foundNode = find(data, true);

        Node<T> toBeDeleted = null;

        for (int i=0; i<foundNode.level; i++) {
            Node<T> tmp = foundNode.next[i].next[i];
            if (tmp !=null && data.compareTo(tmp.data)==0) {
                toBeDeleted = tmp;
                break;
            }
        }

        if (toBeDeleted == null) {
            return false;
        }

        // 这时候 找到了要被删除的节点了，并且找到了它的前驱数组 foundNode.next
        for (int i=0; i<toBeDeleted.level; i++) {
            foundNode.next[i].next[i] = toBeDeleted.next[i];
            toBeDeleted.next[i] = null;
        }

        // 删除之后可能删掉了 maxLevel 的唯一节点，需要重新计算 maxLevel
        calculateMaxLevel();

        size --;
        return true;

    }

    private void calculateMaxLevel() {
        int tmp = maxLevel;
        while (maxLevel > 1 && head.next[tmp-1] == null) {
            tmp --;
        }
        maxLevel = tmp;
    }

    private int compare(Node<T> first, Node<T> second) {
        if (first == second) {
            return 0;
        }

        if (first == MAX_NODE) {
            return 1;
        }

        if (second == MAX_NODE) {
            return -1;
        }

        return first.data.compareTo(second.data);
    }

    public Node<T> get(T data) {
        Node<T> tNode = find(data);
        if (tNode.data == null) {
            return null;
        }
        return tNode;
    }

    private Node<T> find(T data) {
        return find(data, false);
    }

    /**
     * 根据 data 找，从当前jumpTable的 maxLevel 开始<br/>
     * 如果找到了，返回的就是那个Node；<br/>
     * 如果没找到，返回的Node.data=null, next[] 数组就是这个数据各个层级的前驱
     * @param data
     * @return
     */
    private Node<T> find(T data, boolean forDelete) {
        Node<T> newNode = new Node<>(maxLevel, data);

        //noinspection unchecked
        Node<T>[] preNode = new Node[newNode.level];

        for (int i=newNode.level-1; i>=0; i--) {
            Node<T> tmp = head;
            int compareRet;
            while ((compareRet = compare(newNode, tmp.next[i])) > 0) {
                tmp = tmp.next[i];
            }

            // 如果找到相等的了
            if (!forDelete && compareRet == 0) {
                return tmp.next[i];
            }

            preNode[i] = tmp;
        }

        Node<T> tNode = new Node<>(newNode.level, null);
        tNode.next = preNode;

        return tNode;
    }

    public String myToString() {
        int length = this.size;
        int height = this.maxLevel;

        Object[][] array = new Object[length][];

        for (int i=0; i<length; i++) {
            array[i] = new Object[height];
        }

        Node<T> tmp = head;
        for (int i=0; i<length; i++) {
            Object[] tmpArray = array[i];
            tmpArray[0] = tmp.next[0];

            int level = tmp.next[0].level;
            for (int j=level-1; j>0; j--) {
                tmpArray[j] = tmp.next[0];
            }
            tmp = tmp.next[0];
        }

        StringBuilder str = new StringBuilder("size:"+length+", maxLevel:"+height+"\n");
        for (int i=height-1; i>=0; i--) {
            for (int j=0; j<length; j++) {
                str.append(array[j][i]==null?0:array[j][i]).append("\t");
            }
            str.append("\n");
        }

        return str.toString();
    }

    private static final class Node<T> {
        Node(int level, T data) {
            this.level = level;
            this.data = data;
            //noinspection unchecked
            next = new Node[level];
        }

        T data;
        int level;
        Node<T>[] next;

        @Override
        public String toString() {
            return data+"";
        }
    }


    public static void main(String[] args) throws InterruptedException {
        JumpTable<Integer> jumpTable = new JumpTable<>();
        jumpTable.add(1);
        jumpTable.add(2);
        jumpTable.add(3);
        jumpTable.add(4);
        jumpTable.add(5);

        System.out.println(jumpTable.myToString());

        Thread.sleep(1000);

        jumpTable.delete(3);
        System.out.println(jumpTable.myToString());

    }
}
