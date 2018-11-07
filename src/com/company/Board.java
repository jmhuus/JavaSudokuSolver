package com.company;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;

public class Board {

    private final Integer[][] puzzleNums;
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
        System.out.println(toString());

        for(int i=1; i<=9; i++){
            if(isSolution(0,1,i)){
                break;
            }
        }
    }

    public boolean isSolution(int row, int col, int solution){

        puzzleNums[row][col] = solution;
        if(!validateBoard()){
            puzzleNums[row][col] = 0;
            return false;
        }

        System.out.println(toString());

        for(int potentialSolution=1; potentialSolution<=9; potentialSolution++){
            HashMap<String, Integer> nextAddress = getNextAvailableAddress(row, col);
//            System.out.printf("potential solution: %d  row: %s   column: %s\n", potentialSolution, nextAddress.get("row"), nextAddress.get("column"));

            if(isSolution(nextAddress.get("row"), nextAddress.get("column"), potentialSolution)){
                return true;
            }
        }

        puzzleNums[row][col] = 0;

        System.out.println(toString());

        return false;
    }

    public double percentSolved(){
        int count=0;
        for(int row=0; row<9; row++){
            for(int col=0; col<9; col++){
                if(puzzleNums[row][col] != 0){
                    count++;
                }
            }
        }

        return (double)count/81;
    }


    // TODO: refactor into a linked existing linkedList of unsolved cells
    public HashMap<String, Integer> getNextAvailableAddress(int currentRow, int currentCol){
        HashMap<String, Integer> nextAddress = new HashMap<>();

        // Next position from what was passed
        int row;
        int col;
        if(currentCol==8){
            col = 0;
            row = ++currentRow;
        }else{
            col = ++currentCol;
            row = currentRow;
        }

        // "Snake" through each row until an unsolved address is found
        while(true){
            if(puzzleNums[row][col] == 0){
                nextAddress.put("row", row);
                nextAddress.put("column", col);
                return nextAddress;
            }else{
                if(col==8){
                    col = 0;
                    row++;
                }else{
                    col++;
                }
            }

            // Exit condition
            if(row==8 && col==8){
                break;
            }
        }

        // Return blank if non found
        return new HashMap<>();
    }

    public boolean validateBoard(){

        // Validate rows
        for(int row=0; row<9; row++){

            Integer[] sorted = new Integer[puzzleNums[row].length];
            System.arraycopy(puzzleNums[row], 0 , sorted,0, puzzleNums[row].length);
            Arrays.sort(sorted);
            sorted = ArrayUtils.removeAllOccurences(sorted, 0);
            for(int i=1; i<=sorted.length; i++){
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
            for(int i=1; i<=sorted.length; i++){
                if(ArrayUtils.contains(sorted, i)){
                    if(ArrayUtils.indexOf(sorted, i) != ArrayUtils.lastIndexOf(sorted, i)){
                        return false;
                    }
                }
            }
        }

        // Validate grids
        HashMap<Integer, Integer[][]> gridMapping = getGridHashMap();
        for(int i=1; i<=9; i++){
            Integer[][] addressMinMax = gridMapping.get(i);
            int rowMin = addressMinMax[0][0];
            int rowMax = addressMinMax[0][1];
            int colMin = addressMinMax[1][0];
            int colMax = addressMinMax[1][1];


            // Build array from grid
            int[] sorted = new int[]{};
            for(int row=rowMin; row<=rowMax; row++){
                for(int col=colMin; col<=colMax; col++){
                    sorted = ArrayUtils.add(sorted, puzzleNums[row][col]);
                }
            }

            Arrays.sort(sorted);
            sorted = ArrayUtils.removeAllOccurences(sorted, 0);
            for(int x=1; x<=9; x++){
                if(ArrayUtils.contains(sorted, x)){
                    if(ArrayUtils.indexOf(sorted, x) != ArrayUtils.lastIndexOf(sorted, x)){
                        return false;
                    }
                }
            }
        }
        

        return true;
    }


    private HashMap<Integer, Integer[][]> getGridHashMap(){
        // Key=Grid Index     Value = [rowNum range][colNum range]
        HashMap<Integer, Integer[][]> gridMap = new HashMap<>();
        gridMap.put(1, new Integer[][]{{0,2},{0,2}});
        gridMap.put(2, new Integer[][]{{0,2},{3,5}});
        gridMap.put(3, new Integer[][]{{0,2},{6,8}});
        gridMap.put(4, new Integer[][]{{3,5},{0,2}});
        gridMap.put(5, new Integer[][]{{3,5},{3,5}});
        gridMap.put(6, new Integer[][]{{3,5},{6,8}});
        gridMap.put(7, new Integer[][]{{6,8},{0,2}});
        gridMap.put(8, new Integer[][]{{6,8},{3,5}});
        gridMap.put(9, new Integer[][]{{6,8},{6,8}});

        return gridMap;
    }
}











