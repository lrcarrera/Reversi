package com.example.radu.reversi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelsActivity extends AppCompatActivity {

    private static int EASY = 1;
    private static int MEDIUM = 2;
    private static int HARD = 3;
    private Button easy;
    private Button medium;
    private Button hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();

        setContentView(R.layout.activity_levels);

        final Intent configuracio = new Intent(this, ConfiguracioActivity.class);


        easy = (Button) findViewById(R.id.button_facil);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*configuracio.putExtra(getString(R.string.playmode_key), getString(R.string.mode_easy));
                startActivity(configuracio);
                finish();*/
                startParams(EASY);
            }
        });

        medium = (Button) findViewById(R.id.button_medio);
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* configuracio.putExtra(getString(R.string.playmode_key), getString(R.string.mode_medium));
                startActivity(configuracio);
                finish();*/
                startParams(MEDIUM);
            }
        });

        hard = (Button) findViewById(R.id.button_dificil);
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*configuracio.putExtra(getString(R.string.playmode_key), getString(R.string.mode_hard));
                startActivity(configuracio);
                finish();*/
                startParams(HARD);
            }
        });
    }

    private void startParams(int received){
        final Intent configuracio = new Intent(this, ConfiguracioActivity.class);
        if (received == EASY){
            configuracio.putExtra(getString(R.string.playmode_key), getString(R.string.mode_easy));
        } else if(received == MEDIUM){
            configuracio.putExtra(getString(R.string.playmode_key), getString(R.string.mode_medium));
        }else{
            configuracio.putExtra(getString(R.string.playmode_key), getString(R.string.mode_hard));
        }
        startActivity(configuracio);
        finish();
    }
}
