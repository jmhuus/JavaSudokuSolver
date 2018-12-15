package com.company;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {

    GridPane buttonsGrid;
    GridPane sudokuBoardGrid;

    @Override
    public void start(Stage primaryStage) {

        // Root node
        GridPane root = new GridPane();


        // Text boxes
        initSudokuBoard();
        sudokuBoardGrid.setAlignment(Pos.TOP_CENTER);
        sudokuBoardGrid.setPadding(new Insets(15,0,0,0));
        root.add(sudokuBoardGrid, 0,0);


        // Buttons
        initButtons();
        buttonsGrid.setAlignment(Pos.BOTTOM_CENTER);
        buttonsGrid.setPadding(new Insets(0,0,15,0));
        root.add(buttonsGrid, 0, 1);


        // Init window
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Sudoku Solver");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    // Initialize Sudoku board text boxes
    public void initSudokuBoard(){

        // Buttons to grid
        sudokuBoardGrid = new GridPane();
        TextField tf;
        for(int row=0; row<9; row++) {
            for(int col=0; col<9; col++) {


                tf = new TextField();

//                // Spacing for each Sudoku 3x3 grid
//                if(row%3 == 0 && col%3 == 0) {
//                    tf.setPadding(new Insets(0,5,5,0));
//                }else if(row%3 == 0){
//                    tf.setPadding(new Insets(0,0,5,0));
//                }else if(col%3 == 0){
//                    tf.setPadding(new Insets(0,5,0,0));
//                }

                sudokuBoardGrid.add(tf, col, row);
            }
        }


        // Set grid widths
        for(int i=0; i<9; i++) {
            sudokuBoardGrid.getColumnConstraints().add(new ColumnConstraints(25));
        }

    }


    // Initialize buttons onto buttonsGrid
    public void initButtons(){
        Button btnSolve = new Button();
        btnSolve.setText("Solve");
        Button btnReset = new Button();
        btnReset.setText("Reset");
        Button btnClose = new Button();
        btnClose.setText("Close");
        btnSolve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });
        btnReset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });
        btnClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });


        // Add child nodes
        buttonsGrid = new GridPane();
        buttonsGrid.add(btnSolve, 0, 0);
        buttonsGrid.add(btnReset, 1, 0);
        buttonsGrid.add(btnClose, 2, 0);
    }


    // Called after Platform.exit() is called
    @Override
    public void stop() throws Exception {
        super.stop();
    }


    public static void main(String[] args) {
        launch(args);
    }
}














