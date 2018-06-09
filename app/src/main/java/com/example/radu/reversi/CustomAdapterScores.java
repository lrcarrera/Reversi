package com.example.radu.reversi;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class CustomAdapterScores extends SimpleCursorAdapter {

    Activity context;
    Score [] datos;


    public CustomAdapterScores(Context context, int layout, Cursor c, String[] from, int[] to) {
        super(context, layout, c, from, to);
    }
}