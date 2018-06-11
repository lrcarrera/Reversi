package com.example.radu.reversi;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

import com.example.radu.reversi.GameRegisters.Score;

public class CustomAdapterScores extends SimpleCursorAdapter {

    Activity context;
    Score[] datos;


    public CustomAdapterScores(Context context, int layout, Cursor c, String[] from, int[] to) {
        super(context, layout, c, from, to);
    }
}