package com.example.radu.reversi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

public class CustomAdapter extends BaseAdapter {
    private final Context context;
    private Game game;

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
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.row, null);

        ImageView cell = convertView.findViewById(R.id.imaginacion);


        int j = position % game.getBoard().size();
        int i = position / game.getBoard().size();



        if (game.getBoard().isBlack(new Position(i,j))){
            cell.setImageResource(R.drawable.black);
        } else if (game.getBoard().isWhite(new Position(i,j))){
            cell.setImageResource(R.drawable.white);
        } else if (game.getBoard().isEmpty(new Position(i,j))){
            cell.setImageResource(R.drawable.green);
        } /*else if (this.board[x][y].isHint()){
            imageButton.setImageResource(R.drawable.hint);
        }*/

        
        return cell;
    }
}
