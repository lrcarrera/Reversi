package com.example.radu.reversi;

import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapterScores extends ArrayAdapter<Score> {

    Activity context;
    Score [] datos;


    public CustomAdapterScores(Fragment context, Score[] datos) {
        super(context.getActivity(), R.layout.list_item_score, datos);
        this.context = context.getActivity();
        this.datos = datos;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.list_item_score, null);

        TextView lblDe = (TextView)item.findViewById(R.id.LblDe);
        lblDe.setText(datos[position].getDe());

        TextView lblAsunto = (TextView)item.findViewById(R.id.LblAsunto);
        lblAsunto.setText(datos[position].getAsunto());

        return(item);
    }
}