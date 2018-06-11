package com.example.radu.reversi.GameLogic;

import android.os.Parcel;
import android.os.Parcelable;

public class Cell implements Parcelable{
    private static final String WHITE = "white";
    private static final String BLACK = "black";
    private static final String EMPTY = "";
    private static final String OBJECTIVE = "objective";
    private int tranform;

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

    public void setBlack() {
        this.state = BLACK;
        setTransform(0);
    }
    public void setObjective() {
        this.state = OBJECTIVE;
    }

    public void setWhite() {
        this.state = WHITE;
        setTransform(0);
    }

    public void setEmpty() {
        this.state = EMPTY;
    }

    public void setTransform(int tranform){
        this.tranform = tranform;
    }

    public int getTranform(){
        return this.tranform;
    }


    public void reverse(){
        if(this.state == WHITE){
            this.state = BLACK;

        }else if(this.state == BLACK){
            this.state = WHITE;

        }else{
            this.state = EMPTY;

        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.state);
    }

    protected Cell(Parcel in) {
        this.state = in.readString();
    }

    public static final Creator<Cell> CREATOR = new Creator<Cell>() {
        @Override
        public Cell createFromParcel(Parcel source) {
            return new Cell(source);
        }

        @Override
        public Cell[] newArray(int size) {
            return new Cell[size];
        }
    };
}
