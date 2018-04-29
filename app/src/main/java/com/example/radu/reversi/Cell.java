package com.example.radu.reversi;

public class Cell {
    private static final String WHITE = "white";
    private static final String BLACK = "black";
    private static final String EMPTY = "";
    private static final String OBJECTIVE = "objective";

    private String state;

    public Cell(String state){
        this.state = state;
    }

    public static  Cell Empty(){
        return new Cell(EMPTY);
    }

    public boolean isEmpty(){
        return this.state == EMPTY;
    }

    public static  Cell black(){
        return new Cell(BLACK);
    }

    public boolean isBlack(){
        return this.state == BLACK;
    }

    public static  Cell white(){
        return new Cell(WHITE);
    }

    public boolean isWhite(){
        return this.state == WHITE;
    }

    public void reverse(){
        if (isBlack()){
            this.state = WHITE;
        } else{
            this.state = BLACK;
        }
    }


}
