package com.example.radu.reversi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlayModeActivity extends AppCompatActivity {

    private static int isIndividual = 1;
    private static int isMultiPlayer = 2;
    private Button individual;
    private Button multiPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_play_mode);


        individual = (Button) findViewById(R.id.button_individual);
        individual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*startActivity(levels);
                finish();*/
                startParams(isIndividual);
            }
        });

        multiPlayer = (Button) findViewById(R.id.button_multijugador);
        multiPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*configuracio.putExtra(getString(R.string.playmode_key), getString(R.string.multiplayer));
                startActivity(configuracio);
                finish();*/
                startParams(isMultiPlayer);
            }
        });

    }

    private void startParams(int received){
        if (received == isIndividual){
            final Intent levels = new Intent(this, LevelsActivity.class);
            startActivity(levels);
        }else{
            final Intent game = new Intent(this, DesarrolloJuegoActivity.class);
            game.putExtra(getString(R.string.playmode_key), getString(R.string.multiplayer));
            startActivity(game);
        }
        finish();
    }
}
