package com.company;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;

public class Board {

    private Integer[][] puzzleNums;
    private HashMap<String, Cell> cells;


    /**
     * Construct a Board object in order to store and solve the array of Cell objects
     *
     * @param puzzle int[][] puzzle array; [row][column]; zero equals blank
     */
    public Board(Integer[][] puzzle) {
        this.puzzleNums = puzzle;

        // Init empty cells
        cells = new HashMap<>();
    }


    /**
     * Print the Sudoku board
     *
     * @return string representation of the Sudoku board
     */
    @Override
    public String toString() {
        String boardString = "";
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                // Print each cell
                boardString += puzzleNums[row][col] == 0 ? " |" : puzzleNums[row][col] + "|";
            }
            boardString += "\n";
        }

        return boardString;
    }


    public void solve() {

        validateBoard();

    }

    public boolean validateBoard(){

        // Validate rows
        for(int row=0; row<9; row++){
            Integer[] sorted = puzzleNums[row];
            Arrays.sort(sorted);
            sorted = ArrayUtils.removeAllOccurences(sorted, 0);
            for(int i=1; i<=9; i++){
                if(ArrayUtils.contains(sorted, i)){
                    if(ArrayUtils.indexOf(sorted, i) != ArrayUtils.lastIndexOf(sorted, i)){
                        return false;
                    }
                }
            }
        }

        // Validate columns
        for(int column=0; column<9; column++){
            int[] sorted = new int[]{};
            for(int row=0; row<9; row++) {
                sorted = ArrayUtils.add(sorted, puzzleNums[row][column]);
            }
            Arrays.sort(sorted);
            sorted = ArrayUtils.removeAllOccurences(sorted, 0);
            for(int i=1; i<=9; i++){
                if(ArrayUtils.contains(sorted, i)){
                    if(ArrayUtils.indexOf(sorted, i) != ArrayUtils.lastIndexOf(sorted, i)){
                        return false;
                    }
                }
            }
        }

        // Validate grids
        

        return true;
    }
}











