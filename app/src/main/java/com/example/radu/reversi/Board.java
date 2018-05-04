package com.example.radu.reversi;

import android.os.Parcel;
import android.os.Parcelable;

public class Board implements Parcelable{
    private int size;
    public Cell[][] cells;
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

    public int totalCells() {
        return size*size;
    }

    private void initBoard() {

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                this.cells[i][j] = Cell.empty();
            }
        }
        inicialMiddle();
    }

    public void inicialMiddle(){

        initialObjectives();

        cells[(this.size / 2) - 1][(this.size / 2) - 1] = Cell.white();
        cells[this.size / 2][this.size / 2] = Cell.white();
        cells[(this.size / 2) - 1][this.size / 2] = Cell.black();
        cells[this.size / 2][(this.size / 2) - 1] = Cell.black();

        black += 2;
        white += 2;

    }

    public void initialObjectives(){
        cells[this.size/2-2][this.size/2-1] = Cell.objective();
        cells[this.size/2-2][this.size/2-1].setTransform(1);
        cells[this.size/2+1][this.size/2] = Cell.objective();
        cells[this.size/2+1][this.size/2].setTransform(1);
        cells[this.size/2-1][this.size/2-2] = Cell.objective();
        cells[this.size/2-1][this.size/2-2].setTransform(1);
        cells[this.size/2][this.size/2+1] = Cell.objective();
        cells[this.size/2][this.size/2+1].setTransform(1);
    }

    public int getTransform(Position p){
        return cells[p.getColumn()][p.getRow()].getTranform();
    }

    public void setTransform(Position p){
        cells[p.getColumn()][p.getRow()].setTransform(cells[p.getColumn()][p.getRow()].getTranform() + 1);
    }

    public void initialTransform(Position p){
        cells[p.getColumn()][p.getRow()].setTransform(0);
    }

    public boolean contains(Position p) {
        return p.getRow() < size && p.getColumn() < size && p.getRow() >= 0 && p.getColumn() >= 0;
    }

    public boolean isEmpty(Position p) {

        return this.contains(p) && cells[p.getColumn()][p.getRow()].isEmpty();
    }

    public boolean isObjective(Position p) {

        return this.contains(p) && cells[p.getColumn()][p.getRow()].isObjective();

    }


    public boolean isWhite(Position p) {
        return this.contains(p) && cells[p.getColumn()][p.getRow()].isWhite();
    }
    public boolean isBlack(Position p) {
        return this.contains(p) && cells[p.getColumn()][p.getRow()].isBlack();
    }

    public void setWhite(Position p) {
        if (contains(p) && (isEmpty(p)) || isObjective(p)){
            cells[p.getColumn()][p.getRow()].setWhite();
            this.white++;
        }
    }

    public void setBlack(Position p) {
        if (contains(p) && (isEmpty(p)) || isObjective(p))  {
            cells[p.getColumn()][p.getRow()].setBlack();
            this.black++;
        }
    }

    public void setEmpty(Position p) {
        cells[p.getColumn()][p.getRow()].empty();
    }

    public void setObjective(Position p) {
        cells[p.getColumn()][p.getRow()].objective();
    }

    public void reverse(Position p) {
        if (contains(p) && !(isEmpty(p) || isObjective(p)) ) {
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

    public void countAll(int size){
        int objective=0;
        int empty=0;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (this.isObjective(new Position(x, y))) {
                    objective += 1;
                }
                if(this.isEmpty(new Position(x, y))){
                    empty +=1;
                }
            }
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.size);

        for (int i = 0; i < this.size; i++){
            dest.writeTypedArray(this.cells[i], 0);
        }

        dest.writeInt(this.black);
        dest.writeInt(this.white);
    }

    protected Board(Parcel in) {
        this.size = in.readInt();
        //this.cells = in.readParcelable(Cell[][].class.getClassLoader());
        this.black = in.readInt();
        this.white = in.readInt();
        this.cells = new Cell[this.size][];

        for (int i = 0; i < this.size; i++){
            this.cells[i] = in.createTypedArray(Cell.CREATOR);
        }
    }

    public static final Parcelable.Creator<Board> CREATOR = new Creator<Board>() {
        @Override
        public Board createFromParcel(Parcel source) {
            return new Board(source);
        }

        @Override
        public Board[] newArray(int size) {
            return new Board[size];
        }
    };
}
