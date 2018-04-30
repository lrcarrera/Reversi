package com.example.radu.reversi;

public class Position {

    private final int row;
    private final int column;

    public Position(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public Position move(Direction direction) {
        return new Position(this.column + direction.getColumn(), this.row + direction.getRow());
    }
}
