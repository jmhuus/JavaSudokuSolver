package com.company;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

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


    /**
     *
     */
    public void solve() {
        System.out.println(getGridIndex(1,4));
    }


    private int[] getRowOptions(int rowNum){
        // Retrieve row array
        int[] rowNums = puzzleNums[rowNum];

        // Convert primitive to object array
        Integer[] rowNumsObj = new Integer[rowNums.length];
        for(int i=0; i<rowNums.length; i++){
            rowNumsObj[i] = rowNums[i];
        }

        // Remove blanks(zeros)
        List<Integer> existingRowNums = Arrays.asList(rowNumsObj);
        for(int i=0; i<existingRowNums.size(); i++){
            if(existingRowNums.get(i) == 0){
                existingRowNums.remove(i);
            }
        }

        // Convert Object array back to primitive array
        for(int i=0; i+)

        return existingRowNums.toArray();
    }

    private int[] getColOptions(int colNum){
        return new int[]{1};
    }


    /**
     *
     * @param gridNum
     *          Top left:   0
     *          Top left:   1
     *          Top Center: 2
     *          Top Center: 3
     *          Top Center: 4
     *          Top Center: 5
     *          Top Center: 6
     *          Top Center: 7
     *          Top Center: 8
     *          Top Center: 9
     * @return
     *          Array of available numbers in the grid
     */
    private int[] getGridOptions(int gridNum){
        return new int[]{1};
    }

    private int[] getNumOptions(int rowNum, int colNum){

        int[] rowOptions = getRowOptions(rowNum);
        int[] colOptions = getColOptions(colNum);
        int[] gridOptions = getGridOptions(getGridIndex(rowNum, colNum));

        // Concat all three arrays
        int[] uniqueNumOptions = ArrayUtils.addAll(rowOptions, colOptions);
        uniqueNumOptions = ArrayUtils.addAll(uniqueNumOptions, gridOptions);

        return uniqueNumOptions;
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
        // Key=[rowNum range][colNum range]     Value = Grid Index
        HashMap<Integer[][], Integer> gridMapping = new HashMap<>();
        gridMapping.put(new Integer[][]{{1,3},{1,3}}, 1);
        gridMapping.put(new Integer[][]{{1,3},{4,6}}, 2);
        gridMapping.put(new Integer[][]{{1,3},{7,9}}, 3);
        gridMapping.put(new Integer[][]{{4,6},{1,3}}, 4);
        gridMapping.put(new Integer[][]{{4,6},{4,6}}, 5);
        gridMapping.put(new Integer[][]{{4,6},{7,9}}, 6);
        gridMapping.put(new Integer[][]{{7,9},{1,3}}, 7);
        gridMapping.put(new Integer[][]{{7,9},{4,6}}, 8);
        gridMapping.put(new Integer[][]{{7,9},{7,9}}, 9);

        // Retrieve the relevant grid index
        for (HashMap.Entry<Integer[][], Integer> entry : gridMapping.entrySet()) {
            Integer[][] rowColPairs = entry.getKey();
            Integer gridIndex = entry.getValue();
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
}











