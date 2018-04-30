package com.example.radu.reversi;

public class Board {
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
                this.cells[i][j] = Cell.empty();
            }
        }
        inicialMiddle();
    }

    public void inicialMiddle(){

        cells[this.size/2-2][this.size/2-1] = Cell.objective();
        cells[this.size/2+1][this.size/2] = Cell.objective();
        cells[this.size/2-1][this.size/2-2] = Cell.objective();
        cells[this.size/2][this.size/2+1] = Cell.objective();


        cells[(this.size / 2) - 1][(this.size / 2) - 1] = Cell.white();
        cells[this.size / 2][this.size / 2] = Cell.white();
        cells[(this.size / 2) - 1][this.size / 2] = Cell.black();
        cells[this.size / 2][(this.size / 2) - 1] = Cell.black();

        black += 2;
        white += 2;

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


        System.out.println("WHITE:"+getCountWhite()+"\nBLACK:"+getCountBlack()+"\nEMPTY"+empty+"\nOBJECTIVE"+objective);
    }

}
