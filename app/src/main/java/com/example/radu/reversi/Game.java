package com.example.radu.reversi;

import android.widget.Toast;

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

    public void setState(State state){
        this.state = state;
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
        //return (isSame(player, position)) && !(!this.board.contains(position) || (this.board.isEmpty(position))) &&
        //        someSame(player, position.move(direction), direction);
        return !(!this.board.contains(position) || (this.board.isEmpty(position)) || (this.board.isObjective(position))) && ((this.board.isBlack(position) && player.equals(State.BLACK)) || (this.board.isWhite(position) && player.equals(State.WHITE)) || someSame(player, position.move(direction), direction));
    }


    private static boolean allFalse(boolean[] bools) {
        boolean returner = true;
        for (int i = 0; i < bools.length; i++){
            if (bools[i])
                returner = false;
        }
        return  returner;
    }





    private void disk(State player, Position position) {
        if (player == State.WHITE){
            this.board.setWhite(position);
        } else{
            this.board.setBlack(position);
        }
    }
/*
    private void reverseAll(State player, Position position, boolean[] directions){
        for (int i = 0; i < Direction.ALL.length; i++) {
            if (directions[i]) {
                reverse(player, position, Direction.ALL[i]);
            }
        }
    }
*/
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

    private void reverse(Position position, boolean[] directions) {
        for (int i = 0; i < Direction.ALL.length; i++) {
            if (directions[i]) {
                reverse(getState(), position, Direction.ALL[i]);
            }
        }
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

    public boolean canPlayPosition(State player, Position position) {
        return !allFalse(directionsOfReverse(player, position)) && (this.board.isEmpty(position) || this.board.isObjective(position) );
                //this.board.isBlack(position);
    }

    private void changeTurn() {




        //State st1 = State.BLACK;
        if (getState() == State.WHITE)
            //st1 = State.WHITE;
            setState(State.BLACK);
        else{
            setState(State.WHITE);


        }

        if (!canPlay(getState())){
            this.state = State.FINISHED;
        }
/*
        if(getState() == State.WHITE){
            System.out.println("ESPUTOBLANCO");
        }else if(getState() == State.BLACK){
            System.out.println("ESPUTONEGRO");

        }else if(getState() == State.FINISHED){
            System.out.println("esputoFINISHED!");

        }*/


    }


    public boolean isReverseDirection(State player, Position position, Direction direction) {
        Position aux =  position.move(direction);
        return isOther(player, aux) && someSame(player, aux, direction);
    }
    
    public boolean[] directionsOfReverse(State player, Position position) {
        boolean [] returner = new boolean[Direction.ALL.length];
        for (int i = 0; i < Direction.ALL.length; i++){
            returner[i] = isReverseDirection(player, position, Direction.ALL[i]);
            //System.out.println("Direccion: " + Direction.ALL[i].getRow() + "," + Direction.ALL[i].getRow()  + "le puso" + returner[i]);
        }
        return returner;
    }

    public void move(Position position) {
        if (/*!this.board.isEmpty(position) &&*/ !(this.board.isObjective(position) || this.board.isEmpty(position))) {
            return;
        }

        boolean[] directions = this.directionsOfReverse(getState(), position);
        if (allFalse(directions)) {
            return;
        }

        System.out.println("\nPOSITIONNN:"+position.getColumn()+"ROW:"+position.getRow());
        this.disk(getState(), position);
        this.reverse(position, directions);
        this.changeTurn();
    }

    public void setObjectives(int size){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(this.board.isObjective(new Position(i,j))){
                    this.board.setEmpty(new Position(i,j));
                }
                if(canPlayPosition(getState(), new Position(i,j))){
                    this.board.setObjective(new Position(i,j));
                }
            }
        }
    }

    public void phoneTurn(){
        for (int x = 0; x < board.size(); x++) {
            for (int z = 0; z < board.size(); z++) {
                if (board.cells[x][z].isObjective()) {
                    this.move(new Position(x, z));
                    return;
                }
            }
        }
    }


}