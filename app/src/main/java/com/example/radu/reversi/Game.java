package com.example.radu.reversi;

public class Game {

    private Board board;
    private State state;

    public Game(Board board) {

        this.board = board;
        this.state = State.BLACK;
    }

    public int stepsToOutOfBoard(Position position, Direction direction) {
        int numSteps = 0;
        while (this.board.contains(position)) {
            numSteps += 1;
            position = position.move(direction);
        }
        return numSteps;
    }

    public Board getBoard(){
        return this.board;
    }

    public State getState(){
        return this.state;
    }

    public boolean getFinished()
    {
        //AÑADIR CONTROL TIEMPO
       // if((board.getCountBlack() + board.getCountWhite()) == (board.size() * board.size()))
        return this.state == State.FINISHED;
    }

    public boolean isSame(State player, Position position) {
        return (this.board.isWhite(position) && player.equals(State.WHITE)) || (this.board.isBlack(position) && player.equals(State.BLACK));
    }

    public boolean isOther(State player, Position position) {
        return (this.board.isWhite(position) && player.equals(State.BLACK)) || (this.board.isBlack(position) && player.equals(State.WHITE));
    }

    public boolean someSame(State player, Position position, Direction direction) {
        return (isSame(player, position)) && !(!this.board.contains(position) || (this.board.isEmpty(position))) &&
                someSame(player, position.move(direction), direction);
    }

    public boolean isReverseDirection(State player, Position position, Direction direction) {
       Position aux =  position.move(direction);
       return isOther(player, aux) &&
              someSame(player, aux, direction);
    }

    public boolean[] directionsOfReverse(State player, Position position) {
        boolean [] returner = new boolean[Direction.ALL.length];
        for (int i = 0; i < Direction.ALL.length; i++){
            returner[i] = isReverseDirection(player, position, Direction.ALL[i]);
        }
        return returner;
    }

    private static boolean allFalse(boolean[] bools) {
        boolean returner = true;
        for (int i = 0; i < bools.length; i++){
            if (bools[i])
                returner = false;
        }
        return  returner;
    }

    public boolean canPlayPosition(State player, Position position) {
        return !allFalse(directionsOfReverse(player, position)) &&
                this.board.isBlack(position);
    }

    public boolean canPlay(State player) {
        boolean returner = false;
        for (int i = 0; i < this.board.size(); i++){
            for (int j = 0; j < this.board.size(); j++){
                if (canPlayPosition(player, new Position(i, j)))
                    returner = true;
            }
        }
        return returner;
    }

    private void disk(State player, Position position) {
        if (this.state == State.WHITE){
            this.board.setWhite(position);
        } else{
            this.board.setBlack(position);
        }
    }

    private void reverse(State player, Position position, Direction direction) {
        position = position.move(direction);
        if (player == State.WHITE){
            while (this.board.isBlack(position)) {
                this.board.reverse(position);
                position = position.move(direction);
            }
        } else {
            while (this.board.isWhite(position)) {
                this.board.reverse(position);
                position = position.move(direction);
            }
        }
    }

    private void reverseAll(State player, Position position, boolean[] directions){
        for (int i = 0; i < Direction.ALL.length; i++) {
            if (directions[i]) {
                reverse(player, position, Direction.ALL[i]);
            }
        }
    }


}