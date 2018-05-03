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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();

        setContentView(R.layout.activity_desarrollo_juego);

        c1 = getApplicationContext();


        et = (TextView) findViewById(R.id.text);
        gv  = (GridView) findViewById(R.id.grid_custom);
        tv = (TextView) findViewById(R.id.text_fichas);
        count = (TextView) findViewById(R.id.timer_text);

        Intent in = getIntent();
        timer = in.getIntExtra("timer", 0);
        grid_dimension = in.getIntExtra("grid_dimension", 0);
        alias = in.getStringExtra("alias");
        //et.setText(alias + "  " + timer + "  " + grid_dimension);

        gv.setNumColumns(grid_dimension);
       // gv.setColumnWidth();

        Board board = new Board(grid_dimension);
        game = new Game(board);
        State  state = State.BLACK;

        adapter = new CustomAdapter(getApplicationContext(), game, et, tv, timer, count);
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

            et.setText(alias + " " + timer + "  " + grid_dimension);


            //CustomAdapter adapter = (CustomAdapter) gvaux.getAdapter();


            //g.getBoard().countAll(grid_dimension);
            //gv.setColumnWidth(30);;
            adapter = new CustomAdapter(this, game, et, tv, timer, count);
            //gvaux.setAdapter(adapter);
            //adapter.notifyDataSetChanged();
            gv.setAdapter(adapter);

            System.out.println("MAMAPULA"+gv.getColumnWidth());

        }

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
