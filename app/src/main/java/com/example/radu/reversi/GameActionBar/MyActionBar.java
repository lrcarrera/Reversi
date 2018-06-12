package com.example.radu.reversi.GameActionBar;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;


import com.example.radu.reversi.GameRegisters.BddStrings;
import com.example.radu.reversi.R;

public class MyActionBar implements BddStrings {


    public static void showActionBar(Context context, ActionBar actionBar, int num) {
        actionBar.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.GreenActionBar, context.getTheme())));
        switch (num){
            case 0:
                actionBar.setTitle (STRING_REVERSI);
                break;
            case 1:
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setTitle(STRING_CONFIGURACIO);
                break;
            case 2:
                actionBar.setTitle (STRING_REVERSI);
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.show();
                break;
            case 3:
                actionBar.setTitle (STRING_ACCESS);
                actionBar.setDisplayHomeAsUpEnabled(true);
                break;
            case 4:
                actionBar.setTitle(STRING_DETALL);
                actionBar.setDisplayHomeAsUpEnabled(true);
                break;
        }
        actionBar.show();
    }
}
