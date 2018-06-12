package com.example.radu.reversi.GameDevelopment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.example.radu.reversi.GameLogic.Board;
import com.example.radu.reversi.GameLogic.Game;
import com.example.radu.reversi.GameLogic.GameType;
import com.example.radu.reversi.R;


public class DesarrolloJuegoActivity extends FragmentActivity implements ParrillaFrag.MyOnClickListener {


    ParrillaFrag frgParrilla;

    GameType gameType;
    Game game;
    int grid_dimension;
    int timer;
    CustomAdapter adapter;
    TextView count;
    String alias;
    GridView gv;
    TextView et;
    TextView tv;
    Context c1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desarrollo_juego);

        frgParrilla = (ParrillaFrag) getSupportFragmentManager().findFragmentById(R.id.FrgParrilla);
        frgParrilla.setMyOnClickListener(this);
        findViews();
        getSettingsFromPrefs();

        boolean hayDetalle = (getSupportFragmentManager().findFragmentById(R.id.FrgLog) != null);
        if (hayDetalle) {
            TextView txt = findViewById(R.id.TxtDetalleLog);
            frgParrilla.setMyOnClickListener(this);
            if(timer==0) {
                txt.setText(String.format(getString(R.string.temp_init), alias, String.valueOf(grid_dimension), "Sense control temps."));
            }else{
                txt.setText(String.format(getString(R.string.temp_init), alias, String.valueOf(grid_dimension), "Control temps."));
            }
        }
    }

    private void getSettingsFromPrefs(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(DesarrolloJuegoActivity.this);
        if ( prefs.getBoolean(getString(R.string.pref_timer_key),false) ) {
            timer = 1;
        }else{
            timer = 0;
        }
        alias = prefs.getString(getString(R.string.pref_alias_key), getString(R.string.pref_alias_default));
        grid_dimension = Integer.valueOf(prefs.getString(getString(R.string.pref_size_key), getString(R.string.pref_size_default)));

        playModeDecide(prefs.getString(getString(R.string.pref_playmode_key), getString(R.string.pref_playmode_default) ));
        gv.setNumColumns(grid_dimension);
        Board board = new Board(grid_dimension);
        game = new Game(board, gameType, 0);
        adapter = new CustomAdapter(this, game, timer, alias);
        adapter.setMyOnClickListener(this);
        gv.setAdapter(adapter);
    }

    private void findViews(){
        c1 = getApplicationContext();
        et = (TextView) findViewById(R.id.text);
        gv  = (GridView) findViewById(R.id.grid_custom);
        tv = (TextView) findViewById(R.id.text_fichas);
        count = (TextView) findViewById(R.id.timer_text);
    }

    @Override
    public void onLogSeleccionado(String info) {

        boolean hayDetalle = (getSupportFragmentManager().findFragmentById(R.id.FrgLog) != null);

        if(hayDetalle) {
            LogFrag f1 = (LogFrag) getSupportFragmentManager().findFragmentById(R.id.FrgLog);
            f1.mostrarLog(info);
        }
    }


    public void playModeDecide(String playMode){
        if (playMode.equals(getString(R.string.multiplayer))){
            gameType = GameType.MULTIPLAYER;
        } else if (playMode.equals(getString(R.string.mode_easy))){
            gameType = GameType.EASY;
        } else if (playMode.equals(getString(R.string.mode_medium))){
            gameType = GameType.MEDIUM;
        } else {
            gameType = GameType.HARD;
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            game = (Game) savedInstanceState.getParcelable(getString(R.string.game_key));
            adapter = new CustomAdapter(this, game, timer, alias);
            gv.setAdapter(adapter);
            adapter.setMyOnClickListener(this);
            adapter.updateNumbers();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        adapter.stopTimerTask(count);
        outState.putParcelable(getString(R.string.game_key), game);
    }

}
