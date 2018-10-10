package com.company;

import org.apache.commons.lang3.ArrayUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Board{

    private Integer[][] puzzleNums;
    private ArrayList<Cell> cells;


    /**
     * Construct a Board object in order to store and solve the array of Cell objects
     * @param puzzle int[][] puzzle array; [row][column]; zero equals blank
     */
    public Board(Integer[][] puzzle) {
        this.puzzleNums = puzzle;

        // Init empty cells
        cells = new ArrayList<>();
        for(int row=0; row<9; row++){
            for(int col=0; col<9; col++) {


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


    public void solve() {
        System.out.println(Arrays.toString(getRowOptions(1)));
    }


    private Integer[] getRowOptions(int rowNum) {

        // Convert @param:rowNum to be zero-indexed; row 1 == index 0
        rowNum -= 1;

        // Retrieve row array
        Integer[] rowNums = puzzleNums[rowNum];

        // Remove blanks(zeros)
        List<Integer> existingRowNums = new ArrayList<>();
        for(int i=0; i<rowNums.length; i++){
            if(rowNums[i] != 0){
                existingRowNums.add(rowNums[i]);
            }
        }

        // TODO: return numbers that don't already exist, not numbers that do
        Integer[] finalArray = new Integer[existingRowNums.size()];
        finalArray = existingRowNums.toArray(finalArray);

        // Return numbers that don't already exist
        Integer[] nonExistingNums = getNonExistingNums(finalArray);

        return nonExistingNums;
    }


    private Integer[] getColOptions(int colNum){

        // Convert @param:colNum to be zero-indexed; column 1 == index 0
        colNum -= 1;

        // Retrieve column array; Nth item in each row
        Integer[] colNums = new Integer[9];
        for(int i=0; i<9; i++){
            colNums[i] = puzzleNums[i][colNum];
        }

        // Remove blanks(zeros)
        List<Integer> existingColNums = new ArrayList<>();
        for(int i=0; i<colNums.length; i++){
            if(colNums[i] != 0){
                existingColNums.add(colNums[i]);
            }
        }

        // TODO: return numbers that don't already exist, not numbers that do
        Integer[] finalArray = new Integer[existingColNums.size()];
        finalArray = existingColNums.toArray(finalArray);

        return finalArray;
    }


    /**
     * @param gridIndex Number index referencing a 3x3 group of cells
     * @return Array of available numbers in the grid
     */
    private Integer[] getGridOptions(int gridIndex){
        // Retrieve a mapping of each grid and it's contained cell locations
        HashMap<Integer, Integer[][]> gridMap = getGridHashMap();

        // Using @param:gridNum, get the contained cells
        Integer[][] cells = gridMap.get(gridIndex);

        //
        ArrayList<Integer> existingGridNums = new ArrayList<>();
        int currentCellNum = 0;
        for(int rowNum=0; rowNum<cells.length; rowNum++ ){
            for(int colNum=0; colNum<cells.length; colNum++ ){
                currentCellNum = puzzleNums[rowNum][colNum];
                if(currentCellNum != 0){
                    existingGridNums.add(currentCellNum);
                }
            }
        }

        // TODO: return numbers that don't already exist, not numbers that do
        Integer[] finalArray = new Integer[existingGridNums.size()];
        finalArray = existingGridNums.toArray(finalArray);

        return finalArray;
    }


    /**
     * Use various methods to retrieve available row, column and grid number options
     * @param rowNum
     * @param colNum
     * @return
     */
    private Integer[] getNumOptions(int rowNum, int colNum){

        Integer[] rowOptions = getRowOptions(rowNum);
        Integer[] colOptions = getColOptions(colNum);
        Integer[] gridOptions = getGridOptions(getGridIndex(rowNum, colNum));

        // Concat all three arrays
        Integer[] allNumOptions = ArrayUtils.addAll(rowOptions, colOptions);
        allNumOptions = ArrayUtils.addAll(allNumOptions, gridOptions);

        // Use HashSet to retrieve unique numbers
        HashSet<Integer> uniqueNumOptions = new HashSet<>(Arrays.asList(allNumOptions));

        // Convert HashSet to Array
        Integer[] finalArray = new Integer[uniqueNumOptions.size()];
        finalArray = uniqueNumOptions.toArray(finalArray);

        return finalArray;
    }



    private Integer[] getNonExistingNums(Integer[] existingNums){

        // TODO: fix bug
        ArrayList<Integer> nonExistingNums = new ArrayList<>();
        nonExistingNums.add(1);
        nonExistingNums.add(2);
        nonExistingNums.add(3);
        nonExistingNums.add(4);
        nonExistingNums.add(5);
        nonExistingNums.add(6);
        nonExistingNums.add(7);
        nonExistingNums.add(8);
        nonExistingNums.add(9);
        for(int i=0; i<existingNums.length; i++){
            nonExistingNums.remove(existingNums[i]-1);
        }

        // TODO: needs refactoring
        // Convert ArrayList into Integer[]
        Integer[] finalArray = new Integer[9-existingNums.length];
        for(int i=0; i<nonExistingNums.size(); i++){
            if(nonExistingNums.get(i) != 0)
                finalArray[i] = nonExistingNums.get(i);
        }


        return finalArray;

    }


    /**
     * The Sudoku board is comprised of cells and grids
     * @param rowNum contains the relevant cell's location row number
     * @param colNum contains the relevant cell's location column number
     * @return the grid index number. There are 9 grids, each containing a 3x3 group of cells.
     *      1 2 3
     *      4 5 6
     *      7 8 9
     */
    private int getGridIndex(int rowNum, int colNum){
        // Retrieve a mapping of each grid and it's contained cell locations
        HashMap<Integer, Integer[][]> gridMap = getGridHashMap();

        // Use rowNum, colNum to find the gridIndex
        for (Integer gridIndex : gridMap.keySet()) {
            Integer[][] rowColPairs = gridMap.get(gridIndex);
            int mapRowNumMin = rowColPairs[0][0];
            int mapRowNumMax = rowColPairs[0][1];
            int mapColNumMin = rowColPairs[1][0];
            int mapColNumMax = rowColPairs[1][1];

            // Row and column numbers
            if(mapRowNumMin <= rowNum && rowNum <= mapRowNumMax
                    && mapColNumMin <= colNum && colNum <= mapColNumMax){
                return gridIndex;
            }
        }

        return 0;
    }

    private HashMap<Integer, Integer[][]> getGridHashMap(){
        // Key=[rowNum range][colNum range]     Value = Grid Index
        HashMap<Integer, Integer[][]> gridMap = new HashMap<>();
        gridMap.put(1, new Integer[][]{{1,3},{1,3}});
        gridMap.put(2, new Integer[][]{{1,3},{4,6}});
        gridMap.put(3, new Integer[][]{{1,3},{7,9}});
        gridMap.put(4, new Integer[][]{{4,6},{1,3}});
        gridMap.put(5, new Integer[][]{{4,6},{4,6}});
        gridMap.put(6, new Integer[][]{{4,6},{7,9}});
        gridMap.put(7, new Integer[][]{{7,9},{1,3}});
        gridMap.put(8, new Integer[][]{{7,9},{4,6}});
        gridMap.put(9, new Integer[][]{{7,9},{7,9}});

        return gridMap;
    }
}











