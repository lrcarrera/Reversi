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

    public int getCountBlack(){
        return this.black;
    }
    public int getCountWhite(){
        return this.white;
    }

    public int size() {
        return size;
    }

    private void initBoard() {
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                this.cells[i][j] = Cell.Empty();
            }
        }
        incialMiddle();
    }

    public void incialMiddle(){
        int first = (size / 2) - 1;
        setWhite(new Position(first, first));
        setWhite(new Position(first, first + 1));
        setBlack(new Position(first + 1, first));
        setBlack(new Position(first + 1 , first + 1));
    }

    public boolean contains(Position p) {
        return p.getRow() < size && p.getColumn() < size && p.getRow() >= 0 && p.getColumn() >= 0;
    }

    public boolean isEmpty(Position p) {
        return this.contains(p) && cells[p.getRow()][p.getColumn()].isEmpty();
    }

    public boolean isWhite(Position p) {
        return this.contains(p) && cells[p.getRow()][p.getColumn()].isWhite();
    }
    public boolean isBlack(Position p) {
        return this.contains(p) && cells[p.getRow()][p.getColumn()].isBlack();
    }

    public void setWhite(Position p) {
        if (contains(p) && isEmpty(p)){
            cells[p.getRow()][p.getColumn()].white();
            this.white++;
        }
    }

    public void setBlack(Position p) {
        if (contains(p) && !isEmpty(p)) {
            cells[p.getRow()][p.getColumn()].black();
            this.black++;
        }
    }

    public void reverse(Position p) {
        if (contains(p) || !isEmpty(p)) {
            cells[p.getRow()][p.getColumn()].reverse();
            if(cells[p.getRow()][p.getColumn()].isBlack()){
                this.black++;
                this.white--;
            }else{
                this.white++;
                this.black--;
            }
        }
    }

}
