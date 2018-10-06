package com.company;

public class Board{
    public Board() {
    }


    public void solve(Square[] squares){
        boolean squaresUnsolved = true;

        while(squaresUnsolved) {

            // Retrieve answers for each square
            for (Square square : squares) {

            }

            // Check each square for one solution
            for(Square square : squares) {
                if (!square.isSolved()) {
                    squaresUnsolved = true;
                    break;
                }else{
                    squaresUnsolved = false;
                }
            }
        }
    }
}