package com.example.radu.reversi;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.CalendarContract;
import android.widget.Toast;

import java.sql.SQLOutput;

import javax.xml.transform.Source;

public class Game implements Parcelable {

    private Board board;
    private State state;
    private boolean white_play;
    private boolean black_play;
    //private int multiplayer = 1;
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

    public void decreaseDuration(){
        this.gameDuration--;
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
        return !(!this.board.contains(position) ||
                (this.board.isEmpty(position) || this.board.isObjective(position)))
                && ((this.board.isBlack(position) && player.equals(State.BLACK))
                || (this.board.isWhite(position) && player.equals(State.WHITE))
                || someSame(player, position.move(direction), direction));
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

        if (getState() == State.WHITE && gameType != GameType.MULTYPLAYER)
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
        return (this.board.isEmpty(position) || this.board.isObjective(position)) && !allFalse(directionsOfReverse(player, position));
        //this.board.isBlack(position);
    }

    public void setObjectives(int size){


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(this.board.cells[i][j].isObjective()){
                    this.board.cells[i][j] = Cell.empty();
                    this.board.initialTransform(new Position(i,j));

                }
                if(canPlayPosition(getState(), new Position(i,j))){
                    this.board.cells[i][j] = Cell.objective();
                    countMoves(i, j, getState());
                }
            }
        }


    }

    public void phoneTurn(){
        for (int x = 0; x < board.size(); x++) {
            for (int z = 0; z < board.size(); z++) {

                if (board.isObjective(new Position(x,z))){
                    //System.out.println("ENTRO O KELOKE PHONETURN");
                    this.move(new Position(x, z));
                    return;
                }
            }
        }
        /*  System.out.println("Llego al final");
        white_play = false;
        this.changeTurn();*/
    }

    public void countMoves(int i, int j, State state){
        Position p = new Position(i, j);
        // System.out.println("Lo puso como objetivo");
        //State state;
        boolean[] directions = this.directionsOfReverse(getState(), p);
        if (allFalse(directions)) {
            return;
        } else {
            System.out.println("Hay alguna que no es false");
            for (int z = 0; z < Direction.ALL.length; z++) {
                if (directions[z]) {
                    //this.board.setTransform(p);
                    countSubMoves(p.move(Direction.ALL[z]), Direction.ALL[i], i, j);
                }
            }

            /*public int checkFitxesGirades(State estat, Position position){
                boolean [] movimentsValids = directionsOfReverse(estat, position);
                boolean[] result = new boolean[Direction.ALL.length];
                int sumaTotal = 0;
                for (int i = 0; i < movimentsValids.length; i++) {
                    int suma = 0;
                    if(movimentsValids[i]){
                        suma = contarFitxes(position.move(Direction.ALL[i]), Direction.ALL[i], suma);
                        sumaTotal+=suma;

                    }
                }
                return sumaTotal;
            }

            public int contarFitxes(Position position, Direction direction, int suma){
                if(this.board.isBlack(position) && state.equals(State.WHITE)){
                    suma+=1;
                    contarFitxes(position.move(direction), direction, suma);
                }
                return suma;
            }*/

        }
    }

    private void countSubMoves(Position p, Direction direction, int i, int j){
        //System.out.println("Entro en el count Sub movees" + (this.board.isBlack(p)) + (getState() == State.WHITE) );
        System.out.println("Estado: " + getState() + this.board.isBlack(p) + "posicion: " + p.getColumn() + p.getRow());
        if(this.board.isBlack(p) && getState() == State.WHITE){
            System.out.println("Estado white ficha negra");
            this.board.setTransform(new Position(i,j));
            countSubMoves(p.move(direction), direction, i, j);
        } else if (this.board.isWhite(p) && getState() == State.BLACK){
            System.out.println("Estado black ficha blanca");
            this.board.setTransform(new Position(i,j));
            countSubMoves(p.move(direction), direction, i, j);
        }
    }

   /* private void countInterval(Position position, boolean[] directions){
        for (int i = 0; i < Direction.ALL.length; i++) {
            if (directions[i]) {
                partialInterval(getState(), position, Direction.ALL[i]);
            }
        }
    }*/




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