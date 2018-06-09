package com.example.radu.reversi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Parcelable;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DesarrolloJuegoActivity extends FragmentActivity implements ParrillaFrag.MyOnClickListener {


    ParrillaFrag frgParrilla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getActionBar().hide();
        setContentView(R.layout.activity_desarrollo_juego);

        if (savedInstanceState != null) {
            //Restore the fragment's instance
            System.out.println("RADURADU");
            frgParrilla = (ParrillaFrag) getSupportFragmentManager().getFragment(savedInstanceState, "myFragmentName");
        }else{
            frgParrilla = (ParrillaFrag) getSupportFragmentManager().findFragmentById(R.id.FrgParrilla);
        }
        frgParrilla.setMyOnClickListener(this);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("RADURADURADU");
        getSupportFragmentManager().putFragment(outState, "myFragmentName", frgParrilla);
    }

    @Override
    public void onLogSeleccionado(Game g) {

        boolean hayDetalle = (getSupportFragmentManager().findFragmentById(R.id.FrgDetalle) != null);

        if(hayDetalle) {
            LogFrag f1 = (LogFrag) getSupportFragmentManager().findFragmentById(R.id.FrgDetalle);
            f1.mostrarLog(g);
        }
    }
/*
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("ENTRO O KE HACE");

        adapter.stopTimerTask(count);
        outState.putParcelable(getString(R.string.game_key), game);
        outState.putInt(getString(R.string.timer_key), timer);
        outState.putString(getString(R.string.alias_key), alias);
        outState.putInt(getString(R.string.size_key), grid_dimension);
    }*/

   /* @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            game = (Game) savedInstanceState.getParcelable(getString(R.string.game_key));
            timer = savedInstanceState.getInt(getString(R.string.timer_key));
            alias = savedInstanceState.getString(getString(R.string.alias_key));
            grid_dimension = savedInstanceState.getInt(getString(R.string.size_key));
            adapter = new CustomAdapter(this, game, timer,alias);
            gv.setAdapter(adapter);
            adapter.updateNumbers();
        }
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        adapter.stopTimerTask(count);
        outState.putParcelable(getString(R.string.game_key), game);
        outState.putInt(getString(R.string.timer_key), timer);
        outState.putString(getString(R.string.alias_key), alias);
        outState.putInt(getString(R.string.size_key), grid_dimension);
    }*/


        /*public  void updateNumbers(){
        String numbers, state, auxiliar;
        int toFill = (game.getBoard().size() * game.getBoard().size()) -
                (game.getBoard().getCountBlack() + game.getBoard().getCountWhite());
        numbers = String.format(this.getString(R.string.info_caselles),
                Integer.valueOf(game.getBoard().getCountBlack()).toString(),
                Integer.valueOf(game.getBoard().getCountWhite()).toString(),
                Integer.valueOf(toFill).toString());

        tv.setText(numbers);
        if(game.getState() == State.FINISHED){
            auxiliar = this.getString(R.string.finalitzada);
        } else {
            auxiliar = this.getString(R.string.en_marxa);
        }
        state = String.format(this.getString(R.string.estat), auxiliar);
        et.setText(state);
    }*/
}
