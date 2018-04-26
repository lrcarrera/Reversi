package com.example.radu.reversi;

public class Board {
    private final int size;
    private Cell[][] cells;
    private int black;
    private int white;
    public Board(int size) {
        this.size = size;
        this.cells = new Cell[size][size];
        this.black = 0;
        this.white = 0;
        initBoard();
    }

    private void initBoard() {

    }

    public int size() {
        return size;
    }

    public boolean contains(Position p) {
        return false;
    }

    public boolean isEmpty(Position p) {
        return false;
    }

    public boolean isWhite(Position p) {
        return false;
    }
    public boolean isBlack(Position p) {
        return false;
    }

    public void setWhite(Position p) {

    }

    public void setBlack(Position p) {

    }

    public void reverse(Position p) {

    }
}
