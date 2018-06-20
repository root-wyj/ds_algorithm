package com.wyj.ds_algorithm.backtracking;

import java.util.Arrays;

/**
 * Created
 * Author: wyj
 * Email: 18346668711@163.com
 * Date: 2018/6/20
 */
public class Maze {

    int[][] maze;

    int length=0, height=0;

    public Maze(int[][] maze) {
        this.maze = Arrays.copyOf(maze, maze.length);
        height = maze.length;
        length = maze[0].length;
    }

    // 按照下、右、上、左的顺序找路
    public void findPath(int vertical, int horizontal) {

        if (vertical == height-1 && horizontal == length-1) {
            maze[vertical][horizontal] = -1;
            //找到了
            for (int i=0; i<height; i++) {
                for (int j=0; j<length; j++) {
                    System.out.print(String.format("%4d", maze[i][j]));
                }
                System.out.println();
            }
            maze[vertical][horizontal] = 0;
            System.out.println();
        }

        if (vertical + 1 < height && maze[vertical+1][horizontal] == 0) {
            maze[vertical][horizontal] = -1;
            findPath(vertical+1, horizontal);
            maze[vertical][horizontal] = 0;
        }

        if (horizontal + 1 < length && maze[vertical][horizontal+1] == 0){
            maze[vertical][horizontal] = -1;
            findPath(vertical, horizontal+1);
            maze[vertical][horizontal] = 0;
        }

        if (vertical - 1 >= 0 && maze[vertical-1][horizontal] == 0) {
            maze[vertical][horizontal] = -1;
            findPath(vertical-1, horizontal);
            maze[vertical][horizontal] = 0;
        }

        if (horizontal - 1 >= 0 && maze[vertical][horizontal-1] == 0) {
            maze[vertical][horizontal] = -1;
            findPath(vertical, horizontal-1);
            maze[vertical][horizontal] = 0;
        }

    }

    public static void main(String[] args) {
        int[][] mazeData = {
                {0,0,1,0,0,0,1,0},
                {0,0,1,0,0,0,1,0},
                {0,0,1,0,1,1,0,1},
                {0,1,1,1,0,0,1,0},
                {0,0,0,1,0,0,0,0},

                {0,1,0,0,0,1,0,1},
                {0,1,1,1,1,0,0,1},
                {1,1,0,0,0,1,0,1},
                {1,1,0,0,0,0,0,0},
        };

        Maze maze = new Maze(mazeData);
        maze.findPath(0, 0);
    }

}
