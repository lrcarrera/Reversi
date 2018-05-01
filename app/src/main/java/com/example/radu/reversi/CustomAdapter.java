package com.example.radu.reversi;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CustomAdapter extends BaseAdapter {
    private final Context context;
    private Game game;
    private int number = 1;

    public CustomAdapter (Context c, Game game){
        this.context = c;
        this.game = game;
    }

    @Override
    public int getCount() {
        //return 0;
        return this.game.getBoard().totalCells();
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.row, null);

        ImageView cell = convertView.findViewById(R.id.imaginacion);




       /* if (game.getBoard().size() == 6) {
            ///cell.setLa
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(60, 60);
            cell.setLayoutParams(layoutParams);
        } else if (game.getBoard().size() == 4) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(80, 80);
            cell.setLayoutParams(layoutParams);
        }*/
        /*ImageButton cell;

        if (convertView == null) {
            cell = new ImageButton(context);
            cell.setLayoutParams(new GridView.LayoutParams(parent.getWidth() / game.getBoard().size(), parent.getWidth() / game.getBoard().size()));
            cell.setScaleType(ImageView.ScaleType.FIT_CENTER);
            cell.setScaleType(ImageButton.ScaleType.FIT_XY);
            cell.setPadding(0, 0, 0, 0);
        } else {
            cell = (ImageButton) convertView;
        }*/
       /*ImageButton cell;

        if (convertView == null) {
            cell = new ImageButton(context);
            cell.setLayoutParams(new GridView.LayoutParams(parent.getWidth() / game.getBoard().size(), parent.getWidth() /game.getBoard().size()));
            cell.setScaleType(ImageView.ScaleType.FIT_CENTER);
            cell.setScaleType(ImageButton.ScaleType.FIT_XY);
            cell.setPadding(0, 0, 0, 0);
        } else {
            //cell  = convertView.findViewById(R.id.imaginacion);
            cell = (ImageButton) convertView.findViewById(R.id.imaginacion);;
        }*/

        int i = position % game.getBoard().size();
        int j = position / game.getBoard().size();



        if (game.getBoard().isBlack(new Position(i,j))){
            cell.setImageResource(R.drawable.black);
        } else if (game.getBoard().isWhite(new Position(i,j))){
            cell.setImageResource(R.drawable.white);
        } else if (game.getBoard().isEmpty(new Position(i,j))){
            cell.setImageResource(R.drawable.green);
        } else if (game.getBoard().isObjective(new Position(i,j))){
            /*if (number == 1){
                cell.setImageResource(R.drawable.number1);
            } else {
                cell.setImageResource(R.drawable.green);
            }*/
            cell.setImageResource(R.drawable.whitegreen);

        }

        //cell.setOnClickListener(new CellListener(context, new Position(x, y), this.board[x][y], juego));
        return cell;
    }
/*
    public void UpdateGame(Game game) {
        this.game = game;
        notifyDataSetChanged();
    }*/

}
