package com.example.radu.reversi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

public class DesarrolloJuego extends AppCompatActivity {

    Board board;
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desarrollo_juego);

        TextView et = (TextView) findViewById(R.id.text);
        Intent in = getIntent();
        int timer = in.getIntExtra("timer", 0 );
        int grid_dimension = in.getIntExtra("grid_dimension", 0 );
        String alias = in.getStringExtra("alias");
        et.setText(alias + "  " + timer + "  " + grid_dimension);

        GridView gv = (GridView) findViewById(R.id.grid_custom);
        gv.setNumColumns(grid_dimension);

        board = new Board(grid_dimension);
        game = new Game(board);


        CustomAdapter adapter =  new CustomAdapter(this, game);


        gv.setAdapter(adapter);
    }
}
