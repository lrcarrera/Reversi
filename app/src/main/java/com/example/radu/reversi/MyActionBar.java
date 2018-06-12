package com.example.radu.reversi;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;


import com.example.radu.reversi.GameRegisters.BddStrings;

public class MyActionBar implements BddStrings {


    public static void showActionBar(Context context, ActionBar actionBar, int num) {
        switch (num){
            case 0:
                actionBar.setTitle (STRING_REVERSI);
                actionBar.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.colorPrimaryDark, context.getTheme())));
                actionBar.show();
                break;
            case 1:
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setTitle(STRING_CONFIGURACIO);
                break;
            case 2:
                actionBar.setTitle (STRING_REVERSI);
                actionBar.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.colorPrimaryDark, context.getTheme())));
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.show();
                break;
            case 3:
                actionBar.setTitle (STRING_ACCESS);
                actionBar.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.colorPrimaryDark, context.getTheme())));
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.show();
                break;
            case 4:
                actionBar.setTitle(STRING_DETALL);
                actionBar.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.colorPrimaryDark, context.getTheme())));
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.show();
                break;


        }

    }
}
