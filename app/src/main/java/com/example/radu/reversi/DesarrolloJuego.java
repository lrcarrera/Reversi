package com.example.radu.reversi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DesarrolloJuego extends AppCompatActivity {

    int timer;
    Game game;
    String alias;
    int grid_dimension;
    CustomAdapter adapter;
    Context c1;
    GridView gv;
    TextView et;
    TextView tv;
    TextView count;
    GameType gameType;
    ImageView imageTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();

        setContentView(R.layout.activity_desarrollo_juego);

        c1 = getApplicationContext();
        //int gameDuration = 25;


        et = (TextView) findViewById(R.id.text);
        gv  = (GridView) findViewById(R.id.grid_custom);
        tv = (TextView) findViewById(R.id.text_fichas);
        count = (TextView) findViewById(R.id.timer_text);

        Intent in = getIntent();
        timer = in.getIntExtra("timer", 0);





        grid_dimension = in.getIntExtra("grid_dimension", 0);
        alias = in.getStringExtra("alias");


        String playMode = in.getStringExtra("playMode");
        if (playMode.equals("MULTIPLAYER")){
            gameType = GameType.MULTIPLAYER;
        } else if (playMode.equals("EASY")){
            gameType = GameType.EASY;
        } else if (playMode.equals("MEDIUM")){
            gameType = GameType.MEDIUM;
        } else {
            gameType = GameType.HARD;
        }

        gv.setNumColumns(grid_dimension);
       // gv.setColumnWidth();

        Board board = new Board(grid_dimension);
        game = new Game(board, gameType, 0);
        State  state = State.BLACK;

        adapter = new CustomAdapter(getApplicationContext(), game, et, tv, timer, count, alias);
        gv.setAdapter(adapter);
        //adapter.notifyDataSetChanged();




    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        if (savedInstanceState != null) {

            game = (Game) savedInstanceState.getParcelable("key");
            timer = savedInstanceState.getInt("timer");
            alias = savedInstanceState.getString("alias");
            grid_dimension = savedInstanceState.getInt("grid");

            updateNumbers();


            //CustomAdapter adapter = (CustomAdapter) gvaux.getAdapter();


            //g.getBoard().countAll(grid_dimension);
            //gv.setColumnWidth(30);;
            adapter = new CustomAdapter(this, game, et, tv, timer, count, alias);
            //gvaux.setAdapter(adapter);
            //adapter.notifyDataSetChanged();
            gv.setAdapter(adapter);

            System.out.println("MAMAPULA"+gv.getColumnWidth());

        }

    }

    public void callIntent(){
        Intent in = new Intent(this, Resultados.class);
        startActivity(in);
    }


    public  void updateNumbers(){
        String numbers, state, auxiliar;
        int toFill = (game.getBoard().size() * game.getBoard().size()) -
                (game.getBoard().getCountBlack() + game.getBoard().getCountWhite());
        numbers = String.format(this.getString(R.string.info_caselles),
                Integer.valueOf(game.getBoard().getCountBlack()).toString(),
                Integer.valueOf(game.getBoard().getCountWhite()).toString(),
                Integer.valueOf(toFill).toString());

        tv.setText(numbers);
        if(game.getState() == State.FINISHED){
            auxiliar = this.getString(R.string.finalitzada);
        } else {
            auxiliar = this.getString(R.string.en_marxa);
        }
        state = String.format(this.getString(R.string.estat), auxiliar);
        et.setText(state);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("key", game);
        outState.putInt("timer", timer);
        outState.putString("alias", alias);
        outState.putInt("grid", grid_dimension);


    }



}
