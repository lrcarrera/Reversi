package com.example.radu.reversi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class CustomAdapter extends BaseAdapter {
    private final Context context;
    private final int grid_size;

    public CustomAdapter (Context c, int grid_size){
        this.context = c;
        this.grid_size = grid_size;
    }

    @Override
    public int getCount() {
        //return 0;
        return this.grid_size * this.grid_size;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.row, null);

        View cell;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            cell = new View(context);
        } else {
            cell = (View) convertView;
        }
        //cell.setLayoutParams(new GridView.LayoutParams(getCellSize() - grid_size, getCellSize()));
        cell.setTag(position);
        ImageView img = (ImageView) cell.findViewById(R.id.imaginacion);
        /*cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //handle clicks
                ReversiLogic.Coord c = game_model.parsePosition((Integer) v.getTag());
                lstnr.onPlay(c);
                game_model.playSelected(c);
                UpdateStats();
                notifyDataSetChanged();
            }
        });*/
        //call the model to return the cell
        //this.setCell(cell, position);
        return cell;
        //return convertView;
    }
}
