package com.company;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class Controller {

    @FXML private GridPane gridPane_sudokuGrid;

    public Controller() {
        initSudokuGrid();
    }



    // Add text fields to the sudoku grid
    private void initSudokuGrid(){
        TextField tf;
        for(int row=0; row<9; row++){
            for(int col=0; col<9; col++){
                tf = new TextField();
                gridPane_sudokuGrid.add(tf, col, row);
            }
        }
    }
}
