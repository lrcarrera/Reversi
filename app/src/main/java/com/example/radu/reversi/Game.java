package com.example.radu.reversi;

import android.widget.Toast;

import java.sql.SQLOutput;

public class Game {

    private Board board;
    private State state;
    private boolean white_play;
    private boolean black_play;

    public Game(Board board) {

        this.board = board;
        this.state = State.BLACK;
        this.white_play = true;
        this.black_play = true;
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

    public boolean blockFinished(){
        return this.white_play == false && this.black_play == false;
    }

    public boolean isSame(State player, Position position) {
        return (this.board.isWhite(position) && player.equals(State.WHITE)) || (this.board.isBlack(position) && player.equals(State.BLACK));
    }

    public boolean isOther(State player, Position position) {
        return (this.board.isWhite(position) && player==State.BLACK) || (this.board.isBlack(position) && player==State.WHITE);
    }

    public boolean someSame(State player, Position position, Direction direction) {
        //return (isSame(player, position)) && !(!this.board.contains(position) || (this.board.isEmpty(position))) &&
        //        someSame(player, position.move(direction), direction);
        return !(!this.board.contains(position) || (this.board.isEmpty(position) || this.board.isObjective(position))) && ((this.board.isBlack(position) && player.equals(State.BLACK)) || (this.board.isWhite(position) && player.equals(State.WHITE)) || someSame(player, position.move(direction), direction));
    }
/*
    public boolean someSame(State player, Position position, Direction direction) {
        return !(!this.board.contains(position) || (this.board.isEmpty(position))) && ((this.board.isBlack(position) && player.equals(State.BLACK)) || (this.board.isWhite(position) && player.equals(State.WHITE)) || someSame(player, position.move(direction), direction));
    }*/

    public boolean[] directionsOfReverse(State player, Position position) {
        boolean [] returner = new boolean[Direction.ALL.length];
        for (int i = 0; i < Direction.ALL.length; i++){
            returner[i] = isReverseDirection(player, position, Direction.ALL[i]);
            //System.out.println("Direccion: " + Direction.ALL[i].getRow() + "," + Direction.ALL[i].getRow()  + "le puso" + returner[i]);
        }
        return returner;
    }
    private static boolean allFalse(boolean[] bools) {
        //boolean returner = true;
        for (int i = 0; i < bools.length; i++){
            if (bools[i])
                return false;
        }
        return true;
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



    private void changeTurn() {
        if (getState() == State.WHITE)
            //st1 = State.WHITE;
            setState(State.BLACK);
        else{
            setState(State.WHITE);
        }
        //State st1 = State.BLACK;
        //comprove();

        if (!canPlay(State.BLACK) && !canPlay(State.WHITE))
            setState(State.FINISHED);

        if (!canPlay(getState()))
            changeTurn();

        if (getState() == State.WHITE)
            phoneTurn();

        /*if (!canPlay(getState())){
            System.out.println("Entro en la condicion");
            if (getState()  == State.WHITE){
                white_play = false;
                //setState(State.BLACK);
                System.out.println("Puso white a false");
            }
            if (getState()  == State.BLACK){
                black_play = false;
                //setState(State.WHITE);
                System.out.println("Puso black a false");
            }
            if(white_play == false && black_play == false){
                System.out.println("Entro en los dos a false");
                this.state = State.FINISHED;
            }
            if(board.getCountBlack() + board.getCountWhite() == (board.size() * board.size())){
                System.out.println("Todas puestisimas ");
                this.state = State.FINISHED;
            }
        }*/


    }

    /*public void comprove(){
        if (!canPlay(getState())){
            if(board.getCountBlack() + board.getCountWhite() == (board.size() * board.size())){
                System.out.println("Todas puestisimas ");
                this.state = State.FINISHED;
            }
        } else {
            System.out.println("Entro en el if supremo");
            if ((!canPlay(State.WHITE))){
                white_play = false;
                //setState(State.BLACK);
                System.out.println("Puso white a false");
            }
            if ((!canPlay(State.BLACK))){
                black_play = false;
                //setState(State.WHITE);
                System.out.println("Puso black a false");
            }
            if(white_play == false && black_play == false){
                System.out.println("Entro en los dos a false");
                this.state = State.FINISHED;
            }
        }
    }*/


    public boolean isReverseDirection(State player, Position position, Direction direction) {
        Position aux =  position.move(direction);
        return isOther(player, aux) && someSame(player, aux, direction);
    }
    


    public void move(Position position) {
        if (/*!this.board.isEmpty(position) &&*/ !(this.board.isObjective(position) || this.board.isEmpty(position))) {
            return;
        }
        System.out.println("ENTRO O KELOKE, STATE->"+getState().toString()+"POSITION COL:"+position.getColumn()+"ROW: "+position.getRow());

        boolean[] directions = this.directionsOfReverse(getState(), position);
        if (allFalse(directions)) {
            return;
        }
        System.out.println("ENTRO O KELOKE");

        this.disk(getState(), position);
        this.reverse(position, directions);
        this.changeTurn();
    }

    public boolean canPlayPosition(State player, Position position) {
        return (this.board.isEmpty(position) || this.board.isObjective(position)) && !allFalse(directionsOfReverse(player, position));
        //this.board.isBlack(position);
    }

    public void setObjectives(int size){


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(this.board.cells[i][j].isObjective()){
                    this.board.cells[i][j] = Cell.empty();
                }
                if(canPlayPosition(getState(), new Position(i,j))){
                    this.board.cells[i][j] = Cell.objective();
                }
            }
        }


    }

    public void phoneTurn(){
        for (int x = 0; x < board.size(); x++) {
            for (int z = 0; z < board.size(); z++) {

                if (board.isObjective(new Position(x,z))){
                    System.out.println("ENTRO O KELOKE PHONETURN");
                    this.move(new Position(x, z));
                    return;
                }
            }
        }
        /*  System.out.println("Llego al final");
        white_play = false;
        this.changeTurn();*/
    }


}