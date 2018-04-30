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

    public static  Cell empty(){
        return new Cell(EMPTY);
    }
    public static  Cell black(){
        return new Cell(BLACK);
    }
    public static  Cell white(){ return new Cell(WHITE); }
    public static  Cell objective(){ return new Cell(OBJECTIVE); }

    public boolean isEmpty(){
        return this.state == EMPTY;
    }
    public boolean isBlack(){
        return this.state == BLACK;
    }
    public boolean isWhite(){ return this.state == WHITE; }
    public boolean isObjective(){ return this.state == OBJECTIVE; }

    public void setBlack() { this.state = BLACK; }
    public void setObjective() { this.state = OBJECTIVE; }
    public void setWhite() { this.state = WHITE; }
    public void setEmpty() { this.state = EMPTY; }




    public void reverse(){
        if(this.state == WHITE){
            this.state = BLACK;

        }else if(this.state == BLACK){
            this.state = WHITE;

        }else{
            this.state = EMPTY;

        }
    }


}
