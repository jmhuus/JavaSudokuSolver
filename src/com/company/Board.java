package com.company;

import java.util.ArrayList;

public class Board{

    private int[][] puzzleNums;
    private ArrayList<Cell> cells;


    /**
     * Construct a Board object in order to store and solve the array of Cell objects
     * @param puzzle containing an array of cell objects
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


    public void solve(ArrayList<Cell> cells) {

    }
}












