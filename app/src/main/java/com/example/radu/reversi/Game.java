package com.example.radu.reversi;

public class Game {

    private Board board;

    public Game(Board board) {
        this.board = board;
    }

    public boolean getFinished()
    {
        //AÑADIR VERGA TIEMPO BRO
       // if((board.getCountBlack() + board.getCountWhite()) == (board.size() * board.size()))


    }
    /*
    public boolean isSame(??? player, Position position) { ??? }
    public boolean isOther(??? player, Position position) { ??? }
    public boolean someSame(??? player, Position position, Direction direction) { ??? }
    public boolean isReverseDirection(??? player, Position position, Direction direction) { ?? } public boolean[] directionsOfReverse(??? player, Position position) { ??? }
    private static boolean allFalse(boolean[] bools) { ??? }
    public boolean canPlayPosition(State player, Position position, ??? player) { ??? } public boolean canPlay(??? player) { ??? }
    private void disk(Position position, ??? player) { ??? }
    private void reverse(Position position, Direction direction, ??? player) { ??? } private void reverseAA(Position position, boolean[] directions, ??? player) { ??? }
    public void move(Position position, ??? player) { ??? }

    public int stepsToOutOfBoard(Position position, Direction direction) { int numSteps = 0;
        while (this.board.contains(position) {
            numSteps += 1;
            position = position.move(direction);
        }
        return numSteps;
    }
*/


}