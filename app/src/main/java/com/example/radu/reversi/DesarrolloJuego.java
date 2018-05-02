package com.example.radu.reversi;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



public class DesarrolloJuego extends AppCompatActivity {

    Board board;
    Game game;
    State state;
    GridView gv;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desarrollo_juego);

        TextView et = (TextView) findViewById(R.id.text);
        Intent in = getIntent();
        int timer = in.getIntExtra("timer", 0 );
        final int grid_dimension = in.getIntExtra("grid_dimension", 0 );
        String alias = in.getStringExtra("alias");
        et.setText(alias + "  " + timer + "  " + grid_dimension);
        gv  = (GridView) findViewById(R.id.grid_custom);
        gv.setNumColumns(grid_dimension);

        board = new Board(grid_dimension);
        game = new Game(board);
        state = State.BLACK;

        adapter =  new CustomAdapter(this, game);
        gv.setAdapter(adapter);

    }

}
