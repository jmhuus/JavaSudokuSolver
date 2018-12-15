package com.company;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    GridPane buttonsGrid;

    @Override
    public void start(Stage primaryStage) {

        // Text boxes
        initSudokuBoard();

        // Buttons
        initButtons();

        // Init window
        Scene scene = new Scene(buttonsGrid, 300, 250);
        primaryStage.setTitle("Sudoku Solver");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    // Initialize Sudoku board text boxes
    public void initSudokuBoard(){

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


        // Set button constraints(location on grid)
        GridPane.setConstraints(btnSolve, 0, 0);
        GridPane.setConstraints(btnReset, 1, 0);
        GridPane.setConstraints(btnClose, 2, 0);

        // Root node
        buttonsGrid = new GridPane();
        buttonsGrid.setAlignment(Pos.BOTTOM_CENTER);
        buttonsGrid.setPadding(new Insets(0,0,15,0));


        // Add child nodes
        buttonsGrid.getChildren().add(btnReset);
        buttonsGrid.getChildren().add(btnClose);
        buttonsGrid.getChildren().add(btnSolve);
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














