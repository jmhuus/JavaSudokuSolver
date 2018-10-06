package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Board{

    private int[][] puzzleNums;
    private ArrayList<Cell> cells;


    /**
     * Construct a Board object in order to store and solve the array of Cell objects
     * @param puzzle int[][] puzzle array; [row][column]; zero equals blank
     */
    public Board(int[][] puzzle) {
        this.puzzleNums = puzzle;

        // Init empty cells
        cells = new ArrayList<>();
        for(int row=1; row<=9; row++){
            for(int col=1; col<=9; col++) {


                // Init each cell options
                int currentCellNum = puzzleNums[row][col];
                ArrayList<Integer> tempNumOptions;
                if (currentCellNum==0){
                    tempNumOptions = new ArrayList<>();
                    tempNumOptions.add(1);
                    tempNumOptions.add(2);
                    tempNumOptions.add(3);
                    tempNumOptions.add(4);
                    tempNumOptions.add(5);
                    tempNumOptions.add(6);
                    tempNumOptions.add(7);
                    tempNumOptions.add(8);
                    tempNumOptions.add(9);
                    cells.add(new Cell(tempNumOptions, row, col));
                }else{
                    tempNumOptions = new ArrayList<>();
                    tempNumOptions.add(currentCellNum);
                    cells.add(new Cell(tempNumOptions, row, col));
                }
            }
        }
    }

    /**
     * Print the Sudoku board
     * @return string representation of the Sudoku board
     */
    @Override
    public String toString() {
        String boardString="";
        for(int row=0; row<9; row++) {
            for (int col = 0; col < 9; col++) {
                // Print each cell
                boardString += puzzleNums[row][col] == 0 ? " |" : puzzleNums[row][col]+"|";
            }
            boardString += "\n";
        }

        return boardString;
    }


    /**
     *
     * @param cells
     */
    public void solve(ArrayList<Cell> cells) {
        for(Cell cell : cells){

        }
    }


    private int[] getRowOptionsHoriz(int rowNum){

    }

    private int[] getRowOptionsVert(int rowNum){

    }

    private int[] getGridOptions(int gridNum){

    }


    private int[] crossExcludeNums(int[] arr1, int[] arr2){

        int[] newArray = new int[(arr1.length + arr2.length)];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = arr1[i];
        }
        int[] unique = new HashSet<Integer>(Arrays.asList(array)).toArray(new String[0]);
    }
}












