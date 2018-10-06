package com.company;

import java.util.ArrayList;

public class Cell {

    ArrayList<Integer> options;
    int posx, posy;


    public Cell(ArrayList<Integer> options, int posx, int posy) {
        this.options = options;
        this.posx = posx;
        this.posy = posy;
    }


    // Returns whether the square has a known answer
    public boolean isSolved(){
        if(options.size() == 1){
            return true;
        }

        return false;
    }


    // A new number option was found for the square
    public void setOption(int newOption) {
        options.add(newOption);
    }
}
