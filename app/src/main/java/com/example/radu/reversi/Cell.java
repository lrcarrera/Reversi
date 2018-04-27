package com.example.radu.reversi;

public class Cell {
    boolean isEmpty;
    boolean color; /* 0 white 1 black*/

    public Cell(boolean isEmpty, boolean color){
        this.isEmpty = isEmpty;
        this.color =  color;
    }

    public boolean isEmpty(){
        return this.isEmpty();
    }

    public boolean getColor(){
        return this.color;
    }


    public void setColor(boolean color){
        this.color=color;
    }

    public void reverse(){
        this.color = !this.color;
    }
}
