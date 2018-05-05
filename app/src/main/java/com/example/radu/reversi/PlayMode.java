package com.example.radu.reversi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PlayMode extends AppCompatActivity {

    private Button individual;
    private Button multiPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_play_mode);

        final Intent configuracio = new Intent(this, Configuracio.class);
        final Intent levels = new Intent(this, Levels.class);

        individual = (Button) findViewById(R.id.button_individual);
        individual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(levels);
                finish();
            }
        });

        multiPlayer = (Button) findViewById(R.id.button_multijugador);
        multiPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configuracio.putExtra(getString(R.string.playmode_key), getString(R.string.multiplayer));
                startActivity(configuracio);
                finish();
            }
        });

    }
}
