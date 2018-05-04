package com.example.radu.reversi;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.CalendarContract;
import android.widget.Toast;

import java.sql.SQLOutput;
import java.util.IllegalFormatCodePointException;
import java.util.concurrent.ThreadLocalRandom;

import javax.xml.transform.Source;

public class Game implements Parcelable {

    private Board board;
    private State state;
    private boolean white_play;
    private boolean black_play;
    private int gameDuration;
    private GameType gameType;

    public Game(Board board, GameType type, int gameDuration) {

        this.board = board;
        this.state = State.BLACK;
        this.white_play = true;
        this.black_play = true;
        this.gameType = type;
        this.gameDuration = gameDuration;
    }

    public void increaseDuration(){
        this.gameDuration++;
    }

    public int getGameDuration(){
        return this.gameDuration;

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

    public GameType getGameType(){
        return this.gameType;
    }


    public State getState(){
        return this.state;
    }

    public boolean getFinished()
    {

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
        return !(!this.board.contains(position) ||
                (this.board.isEmpty(position) || this.board.isObjective(position)))
                && ((this.board.isBlack(position) && player.equals(State.BLACK))
                || (this.board.isWhite(position) && player.equals(State.WHITE))
                || someSame(player, position.move(direction), direction));
    }


    public boolean[] directionsOfReverse(State player, Position position) {
        boolean [] returner = new boolean[Direction.ALL.length];
        for (int i = 0; i < Direction.ALL.length; i++){
            returner[i] = isReverseDirection(player, position, Direction.ALL[i]);

        }
        return returner;
    }
    private static boolean allFalse(boolean[] bools) {
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
        if (getState() == State.WHITE) {
            setState(State.BLACK);
        }else{
            setState(State.WHITE);
        }

        if (!canPlay(State.BLACK) && !canPlay(State.WHITE)) {
            setState(State.FINISHED);
            return;
        }

        if (!canPlay(getState()))
            changeTurn();

        if (getState() == State.WHITE && gameType != GameType.MULTIPLAYER)
            phoneTurn();

    }


    public boolean isReverseDirection(State player, Position position, Direction direction) {
        Position aux =  position.move(direction);
        return isOther(player, aux) && someSame(player, aux, direction);
    }
    


    public void move(Position position) {
        if (!(this.board.isObjective(position) || this.board.isEmpty(position))) {
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
        //return (this.board.isEmpty(position) || this.board.isObjective(position)) && !allFalse(directionsOfReverse(player, position));
        return this.board.isEmpty(position) && !allFalse(directionsOfReverse(player, position));
    }

    public void setObjectives(int size){


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board.initialTransform(new Position(i,j));
                //System.out.println("Al entrar:" + i + j + "valor: " + this.board.cells[i][j].getTranform());
                if(this.board.cells[i][j].isObjective()){
                    this.board.cells[i][j] = Cell.empty();

                }
                if(canPlayPosition(getState(), new Position(i,j))){
                    this.board.cells[i][j] = Cell.objective();
                    countMoves(i, j, getState());
                }
                //System.out.println("Al salir:" + i + j + "valor: " + this.board.cells[i][j].getTranform());
            }
        }


    }

    public void phoneTurn(){
        int maxToTransform, minToTransform;
        Position max, min, normal;
        max = new Position(0,0);
        min = new Position(0,0);
        //normal = new Position(0,0);
        maxToTransform = 0;
        minToTransform = 999999;
        for (int x = 0; x < board.size(); x++) {
            for (int z = 0; z < board.size(); z++) {
                Position aux = new Position(x,z);
                if (board.isObjective(aux)){
                    //board.getTransform()
                    //board.getTransform(aux);

                   /* if (board.getTransform(aux) <= maxToTransform){
                        //maxToTransform = board.getTransform(aux);
                        System.out.println("Medio" + maxToTransform + "casilla:" + x + z);
                        normal = new Position(x,z);

                    }*/

                    if (board.getTransform(aux) >= maxToTransform){
                        maxToTransform = board.getTransform(aux);
                        System.out.println("MAX" + maxToTransform + "casilla:" + x + z);
                        max = new Position(x,z);
                    }

                    if (board.getTransform(aux) <= minToTransform){
                        maxToTransform = board.getTransform(aux);
                        System.out.println("Min" + maxToTransform + "casilla:" + x + z);
                        min = new Position(x,z);
                    }

                    //System.out.println("ENTRO O KELOKE PHONETURN");
                   //this.move(new Position(x, z));
                    //return;
                }
            }
        }

        if (gameType == GameType.EASY) {
            System.out.println("Entro en facil");
            this.move(min);
        } else if (gameType == GameType.MEDIUM) {
            System.out.println("Entro en medio");
            int randomNum = ThreadLocalRandom.current().nextInt(0, 1 + 1);
            if (randomNum == 0){
                this.move(min);
            } else {
                this.move(max);
            }

            //this.move(normal);
        } else if (gameType == GameType.HARD) {
            System.out.println("Enttro en normal");
            this.move(max);
        }

        /*  System.out.println("Llego al final");
        white_play = false;
        this.changeTurn();*/
    }

    public void countMoves(int i, int j, State state){
        Position p = new Position(i, j);
        boolean[] directions = this.directionsOfReverse(getState(), p);
        if (allFalse(directions)) {
            return;
        } else {
            for (int z = 0; z < Direction.ALL.length; z++) {
                if (directions[z]) {
                    countSubMoves(p.move(Direction.ALL[z]), Direction.ALL[i], i, j);
                }
            }

        }
    }

    private void countSubMoves(Position p, Direction direction, int i, int j){
        if(this.board.isBlack(p) && getState() == State.WHITE){
            this.board.setTransform(new Position(i,j));
            countSubMoves(p.move(direction), direction, i, j);
        } else if (this.board.isWhite(p) && getState() == State.BLACK){
            this.board.setTransform(new Position(i,j));
            countSubMoves(p.move(direction), direction, i, j);
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.board, flags);
        dest.writeInt(this.state == null ? -1 : this.state.ordinal());
        dest.writeByte(this.white_play ? (byte) 1 : (byte) 0);
        dest.writeByte(this.black_play ? (byte) 1 : (byte) 0);
    }

    protected Game(Parcel in) {
        this.board = in.readParcelable(Board.class.getClassLoader());
        int tmpState = in.readInt();
        this.state = tmpState == -1 ? null : State.values()[tmpState];
        this.white_play = in.readByte() != 0;
        this.black_play = in.readByte() != 0;
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel source) {
            return new Game(source);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
}