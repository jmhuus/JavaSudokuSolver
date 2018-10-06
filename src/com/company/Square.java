package com.company;

import java.util.ArrayList;

public class Square {

    ArrayList<Integer> options;


    public Square(ArrayList<Integer> options) {
        this.options = options;
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
