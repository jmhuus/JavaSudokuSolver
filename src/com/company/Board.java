package com.company;

import org.apache.commons.lang3.ArrayUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Board{

    private Integer[][] puzzleNums;
    private HashMap<String, Cell> cells;


    /**
     * Construct a Board object in order to store and solve the array of Cell objects
     * @param puzzle int[][] puzzle array; [row][column]; zero equals blank
     */
    public Board(Integer[][] puzzle) {
        this.puzzleNums = puzzle;

        // Init empty cells
        cells = new HashMap<>();
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

        for(int i=0; i<15; i++){
            firstSolveStrategy();
        }

        updateCellsArrayList();
        xWingStrategy();
        updateBoardWithSingleCellOptions();
        cells = new HashMap<>();
        System.out.println(toString()+"\n");


        updateCellsArrayList();
        xWingStrategy();
        updateBoardWithSingleCellOptions();
        cells = new HashMap<>();
        System.out.println(toString()+"\n");

    }

    public void updateBoardWithSingleCellOptions(){
        // Test for single Cells that have only one number option left
        for(String address: cells.keySet()){

            // One available number option
            if(cells.get(address).getOptions().length == 1) {

                // Place number option into the board
                int row = Integer.parseInt(address.substring(0, 1));
                int col = Integer.parseInt(address.substring(1, 2));
                int solvedNum = cells.get(address).getOptions()[0];
                System.out.println(address+"  "+solvedNum);
                puzzleNums[row-1][col-1] = solvedNum;
            }
        }
    }


    public void firstSolveStrategy(){
        // First attempt at solving the board
        for(int rowIndex=0; rowIndex<9; rowIndex++){
            for(int colIndex=0; colIndex<9; colIndex++) {

                // Init each cell option
                int currentCellNum = puzzleNums[rowIndex][colIndex];
                Integer[] numOptions;
                if (currentCellNum==0){
                    numOptions = getNumOptions(rowIndex+1, colIndex+1);

                    // Reassign the solved number option -or- store the number options
                    if(numOptions.length == 1) {
                        puzzleNums[rowIndex][colIndex] = numOptions[0];
                    }
                }
            }
        }
    }

    /**
     *  X-Wing sudoku strategy is an advanced solving technique - look online for details
     */
    public void xWingStrategy(){

        // 5 because the other cells will reference other board locations that are 4 removed
        for(int row=1; row<=6; row++){
            for(int col=1; col<=6; col++){
                // Four corner Addresses; rowIndex=1 & colIndex=4   =>   address=14
                String topLeftAddress = ""+(row)+""+(col);
                String topRightAddress = ""+(row)+""+(col+3);
                String bottomLeftAddress = ""+(row+3)+""+(col);
                String bottomRightAddress = ""+(row+3)+""+(col+3);

                // Retrieve four corner number options
                Integer[] topLeftOptions;
                Integer[] topRightOptions;
                Integer[] bottomLeftOptions;
                Integer[] bottomRightOptions;


                // Retrieve each X Wing corner number options
                // If any of the four corners is already solved => skip(continue)
                if(cells.get(topLeftAddress) != null){topLeftOptions = cells.get(topLeftAddress).getOptions();}else{continue;}
                if(cells.get(topRightAddress) != null){topRightOptions = cells.get(topRightAddress).getOptions();}else{continue;}
                if(cells.get(bottomLeftAddress) != null){bottomLeftOptions = cells.get(bottomLeftAddress).getOptions();}else{continue;}
                if(cells.get(bottomRightAddress) != null){bottomRightOptions = cells.get(bottomRightAddress).getOptions();}else{continue;}


                // Search for numbers that exist in all four number options
                Integer[] allNumOptions = ArrayUtils.addAll(topLeftOptions, topRightOptions);
                allNumOptions = ArrayUtils.addAll(allNumOptions, bottomLeftOptions);
                allNumOptions = ArrayUtils.addAll(allNumOptions, bottomRightOptions);

                // Sort the number options
                Arrays.sort(allNumOptions);

                // Using the sorted array, search for instances of four number occurrences
                int numCount = 0;
                int currentNum = allNumOptions[0];
                for(int i=1; i<allNumOptions.length; i++){
                    int random = allNumOptions[i];
                    if(currentNum == allNumOptions[i]){

                        // Four of the same number option were found; four numbers found == three consecutive matches
                        if(++numCount == 3){

                            // Eliminate currentNum from number options in cells located in the current column and row
                            String addressesToExclude[] = new String[]{topLeftAddress, topRightAddress, bottomLeftAddress, bottomRightAddress};
                            removeFromRow(currentNum, row, addressesToExclude);
                            removeFromRow(currentNum, row+3, addressesToExclude);
                        }
                    }else{
                        currentNum = allNumOptions[i];
                    }
                }
            }
        }
    }

    public void removeFromRow(int numberToRemove, int rowNum, String excludeAddresses[]){
        for(int colIndex=1; colIndex<=9; colIndex++){
            // Build string address
            String address = ""+rowNum+""+(colIndex);

            // Retrieve cell object
            Cell currentCell = cells.get(address);

            // Remove the number from the entire row,
            if(currentCell == null || ArrayUtils.indexOf(excludeAddresses, address)>-1) continue;
            cells.replace(address, new Cell(ArrayUtils.removeElement(currentCell.getOptions(), numberToRemove),rowNum, colIndex));
        }
    }

    public void updateCellsArrayList(){
        for(int rowIndex=0; rowIndex<9; rowIndex++){
            for(int colIndex=0; colIndex<9; colIndex++){
                int currentCellNum = puzzleNums[rowIndex][colIndex];

                Integer[] numOptions = getNumOptions(rowIndex+1, colIndex+1);
                if(currentCellNum == 0) {
                    cells.put(""+(rowIndex+1)+""+(colIndex+1), new Cell(numOptions, rowIndex + 1, colIndex + 1));
                }
            }
        }
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

        // Convert List<> to Integer[]
        Integer[] existingRowNums2 = new Integer[existingRowNums.size()];
        existingRowNums2 = existingRowNums.toArray(existingRowNums2);

        // Return numbers that don't already exist
        Integer[] nonExistingRowNums = getNonExistingNums(existingRowNums2);

        return nonExistingRowNums;
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

        // Convert List<> to Integer[]
        Integer[] existingColNums2 = new Integer[existingColNums.size()];
        existingColNums2 = existingColNums.toArray(existingColNums2);

        // Return numbers that don't already exist
        Integer[] nonExistingRowNums = getNonExistingNums(existingColNums2);

        return nonExistingRowNums;
    }

    private Integer[] getGridOptions(int gridIndex){
        // All grids
        HashMap<Integer, Integer[][]> gridMap = getGridHashMap();

        // Get specified grid
        Integer[][] cells = gridMap.get(gridIndex);
        int rowIndexMin = cells[0][0]-1;
        int rowIndexMax = cells[0][1]-1;
        int colIndexMin = cells[1][0]-1;
        int colIndexMax = cells[1][1]-1;

        //
        ArrayList<Integer> existingGridNums = new ArrayList<>();
        int currentCellNum = 0;
        for(int rowIndex=rowIndexMin; rowIndex<=rowIndexMax; rowIndex++ ){
            for(int colIndex=colIndexMin; colIndex<=colIndexMax; colIndex++ ){
                currentCellNum = puzzleNums[rowIndex][colIndex];
                if(currentCellNum != 0){
                    existingGridNums.add(currentCellNum);
                }
            }
        }

        // Convert List<> to Integer[]
        Integer[] existingGridNums2 = new Integer[existingGridNums.size()];
        existingGridNums2 = existingGridNums.toArray(existingGridNums2);

        // Return numbers that don't already exist
        Integer[] nonExistingGridNums = getNonExistingNums(existingGridNums2);

        return nonExistingGridNums;
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

        // Use all number options to find the final(actually number options)
        ArrayList<Integer> finalNumOptions = new ArrayList<>();
        for(int currNum=1; currNum<=9; currNum++){
            int currNumCount = 0;
            for(Integer allNumsOption: allNumOptions){
                if(currNum==allNumsOption){
                    currNumCount += 1;
                }
            }

            // Number option must exist three times within allNumOptions in order to be a valid finalNumOption
            if(currNumCount==3){
                finalNumOptions.add(currNum);
            }
        }


        Integer[] finalNumOptionsArray = new Integer[finalNumOptions.size()];
        finalNumOptionsArray = finalNumOptions.toArray(finalNumOptionsArray);

        return finalNumOptionsArray;
    }


    /**
     * Methods getRowOptions(), getColOptions(), and getGridOptions() retrieve already existing numbers.
     * In order to return possible solution numbers for a given cell, use getNonExistingNums.
     * @param existingNums provides existing row, column, and grid numbers.
     * @return an array of possible number solutions.
     */
    private Integer[] getNonExistingNums(Integer[] existingNums){

        // TODO: Needs refactoring
        // Default available numbers 1-9
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

        // Remove numbers that already exist
        for(int i=0; i<existingNums.length; i++){
            nonExistingNums.remove(existingNums[i]);
        }

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











