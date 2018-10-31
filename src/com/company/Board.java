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
        System.out.println(validateBoard());

    }

    public boolean validateBoard(){

        // Validate rows
        for(int row=0; row<9; row++){
            Integer[] sorted = puzzleNums[row];
            Arrays.sort(sorted);
            sorted = ArrayUtils.removeAllOccurences(sorted, 0);
            System.out.println(Arrays.toString(sorted));
            for(int i=1; i<=sorted.length; i++){
                if(ArrayUtils.contains(sorted, i)){
                    if(ArrayUtils.indexOf(sorted, i) != ArrayUtils.lastIndexOf(sorted, i)){
                        return false;
                    }
                }
            }
        }

        System.out.println(toString());

        // Validate columns
        for(int column=0; column<9; column++){
            int[] sorted = new int[]{};
            for(int row=0; row<9; row++) {
                System.out.printf("row %d column %d  = %d\n", row, column, puzzleNums[row][column]);
                sorted = ArrayUtils.add(sorted, puzzleNums[row][column]);
            }

            System.out.println(Arrays.toString(sorted));
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
            for(int x=0; x<sorted.length; x++){
                if(ArrayUtils.contains(sorted, i)){
                    if(ArrayUtils.indexOf(sorted, i) != ArrayUtils.lastIndexOf(sorted, i)){
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











