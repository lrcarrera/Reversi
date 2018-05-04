package com.example.radu.reversi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Levels extends AppCompatActivity {

    private Button easy;
    private Button medium;
    private Button hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();

        setContentView(R.layout.activity_levels);

        final Intent configuracio = new Intent(this, Configuracio.class);


        easy = (Button) findViewById(R.id.button_facil);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configuracio.putExtra("playMode", "EASY");
                startActivity(configuracio);
                finish();
            }
        });

        medium = (Button) findViewById(R.id.button_medio);
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configuracio.putExtra("playMode", "MEDIUM");
                startActivity(configuracio);
                finish();
            }
        });

        hard = (Button) findViewById(R.id.button_dificil);
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configuracio.putExtra("playMode", "HARD");
                startActivity(configuracio);
                finish();
            }
        });
    }
}
