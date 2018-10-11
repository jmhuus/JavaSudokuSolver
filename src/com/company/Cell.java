package com.company;

import java.util.ArrayList;

public class Cell {

    Integer[] options;
    int posx, posy;


    public Cell(Integer[] options, int posx, int posy) {
        this.options = options;
        this.posx = posx;
        this.posy = posy;
    }


    // Returns whether the square has a known answer
    public boolean isSolved(){
        if(options.length == 1){
            return true;
        }

        return false;
    }


    // TODO: include functionality to add a number option
//    public void appendNumOption(int newOption){
//
//    }
}
