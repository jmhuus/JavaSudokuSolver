package com.company;

public class Main {

    public static void main(String[] args) {
        // [row][column]
        int[][] boardNums = {
                {5,0,0,9,0,0,0,2,7},
                {0,0,0,5,0,0,1,0,4},
                {0,0,7,1,2,3,0,0,8},
                {0,0,6,0,0,4,0,0,2},
                {4,8,0,0,0,0,0,1,6},
                {9,0,0,6,0,0,5,0,0},
                {7,0,0,3,9,5,2,0,0},
                {8,0,9,0,0,6,0,0,0},
                {1,5,0,0,0,2,0,0,9},
        };
        Board board = new Board(boardNums);
        System.out.println(board.toString());
    }
}

