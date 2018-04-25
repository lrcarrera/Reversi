package com.example.radu.reversi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class CustomAdapter extends BaseAdapter {
    private final Context context;

    public CustomAdapter (Context c){
        this.context = c;
    }

    @Override
    public int getCount() {
        return 0;
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
        //View row;

       /* if (type.equals("grid")){
            rowView = inflater.inflate(R.layout.rowlayoutgrid, parent, false);
        } else {
            rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        }*/

       // TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imaginacion);

       // imageView.setImageResource(R.draw);

       // checkLogo(s, imageView);

        return convertView;
    }
}
