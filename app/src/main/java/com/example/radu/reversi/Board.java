package com.example.radu.reversi;

public class Board {
    private int size;
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
   /* public Cell getCell(Position position){
        this.cells[position.getColumn()][position.getRow()].;
    }*/

    public int size() {
        return size;
    }

    public int totalCells() {
        return size*size;
    }

    private void initBoard() {

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                this.cells[i][j] = Cell.Empty();
            }
        }
        inicialMiddle();
    }

    public void inicialMiddle(){

        this.cells[(this.size / 2) - 1][(this.size / 2) - 1] = Cell.white();
        this.cells[this.size / 2][this.size / 2] = Cell.white();
        this.cells[(this.size / 2) - 1][this.size / 2] = Cell.black();
        this.cells[this.size / 2][(this.size / 2) - 1] = Cell.black();

        this.black += 2;
        this.white += 2;

    }

    public boolean contains(Position p) {
        return p.getRow() < size && p.getColumn() < size && p.getRow() >= 0 && p.getColumn() >= 0;
    }

    public boolean isEmpty(Position p) {
        return this.contains(p) && cells[p.getColumn()][p.getRow()].isEmpty();
    }

    public boolean isWhite(Position p) {
        return this.contains(p) && cells[p.getColumn()][p.getRow()].isWhite();
    }
    public boolean isBlack(Position p) {
        return this.contains(p) && cells[p.getColumn()][p.getRow()].isBlack();
    }

    public void setWhite(Position p) {
        if (contains(p) && isEmpty(p)){
            cells[p.getColumn()][p.getRow()].white();
            this.white++;
        }
    }

    public void setBlack(Position p) {
        if (contains(p) && isEmpty(p)) {
            cells[p.getColumn()][p.getRow()].black();
            this.black++;
        }
    }

    public void reverse(Position p) {
        if (contains(p) && !isEmpty(p)) {
            cells[p.getColumn()][p.getRow()].reverse();
            if(cells[p.getColumn()][p.getRow()].isBlack()){
                this.black++;
                this.white--;
            }else{
                this.white++;
                this.black--;
            }
        }
    }

}
