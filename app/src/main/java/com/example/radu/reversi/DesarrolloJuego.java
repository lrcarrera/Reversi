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
        timer = in.getIntExtra(getString(R.string.timer_key), 0);





        grid_dimension = in.getIntExtra(getString(R.string.size_key), 0);
        alias = in.getStringExtra(getString(R.string.alias_key));


        String playMode = in.getStringExtra(getString(R.string.playmode_key));
        if (playMode.equals(getString(R.string.multiplayer))){
            gameType = GameType.MULTIPLAYER;
        } else if (playMode.equals(getString(R.string.mode_easy))){
            gameType = GameType.EASY;
        } else if (playMode.equals(getString(R.string.mode_medium))){
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

            game = (Game) savedInstanceState.getParcelable(getString(R.string.game_key));
            timer = savedInstanceState.getInt(getString(R.string.timer_key));
            alias = savedInstanceState.getString(getString(R.string.alias_key));
            grid_dimension = savedInstanceState.getInt(getString(R.string.size_key));

            updateNumbers();


            //CustomAdapter adapter = (CustomAdapter) gvaux.getAdapter();


            //g.getBoard().countAll(grid_dimension);
            //gv.setColumnWidth(30);;
            adapter = new CustomAdapter(this, game, et, tv, timer, count, alias);
            //gvaux.setAdapter(adapter);
            //adapter.notifyDataSetChanged();
            gv.setAdapter(adapter);

        }

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
        outState.putParcelable(getString(R.string.game_key), game);
        outState.putInt(getString(R.string.timer_key), timer);
        outState.putString(getString(R.string.alias_key), alias);
        outState.putInt(getString(R.string.size_key), grid_dimension);


    }



}
