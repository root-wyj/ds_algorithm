package com.wyj.ds_algorithm.backtracking;

import java.util.Arrays;

/**
 * 回溯算法
 * Author: wyj
 * Email: 18346668711@163.com
 * Date: 2018/6/20
 */
public class AllocateBook {

    // 人喜欢书
    int[][] likes;

    // index 是书，内容是人
    int[] given;

    int count;


    public AllocateBook(int count, int[][] likes) {
        //likes 的length = count，likes[0].length=count
        this.count = count;
        this.given = new int[count];
        this.likes = new int[count][count];
        for (int i = 0; i<count; i++) {
            given[i] = -1;
        }

        for (int i=0; i<count; i++) {
            for (int j=0; j<count; j++) {
                this.likes[i][j] =likes[i][j];
            }
        }
    }

    //思路是 先分配第0个人的书，然后再分配第一个人的书，依次类推
    // 如果某书已经被分配，将数据保存起来，方便下次匹配以及之后的回溯使用
    public void allocateBook(int curPeople) {

        for (int bookIndex=0; bookIndex<likes[curPeople].length; bookIndex++) {

            //如果这个人喜欢这本书，并且这本书还没有被分配
            if (likes[curPeople][bookIndex] == 1 && given[bookIndex] == -1) {
                //将这本书分配给它
                given[bookIndex] = curPeople;

                //如果这时候最后一个人也分配完了
                if (curPeople == count - 1) {
                    //打印结果
                    for (int i=0; i<count; i++) {
                        System.out.println(String.format("将第%d本书分给第%d个人", i+1, given[i]+1));
                    }
                    System.out.println();
                } else {
                    //继续分配下一个人
                    allocateBook(curPeople + 1);
                }

                given[bookIndex] = -1;

            }

        }

    }

    public static void main(String[] args) {
        int[][] likes = new int[][]{
                { 0, 0, 1, 1, 0 },
                { 1, 1, 0, 0, 1 },
                { 0, 1, 1, 0, 1 },
                { 0, 0, 0, 1, 0 },
                { 0, 1, 0, 0, 1 }
        };
        int count = 5;
        AllocateBook back = new AllocateBook(count, likes);
        back.allocateBook(0);
    }

}
