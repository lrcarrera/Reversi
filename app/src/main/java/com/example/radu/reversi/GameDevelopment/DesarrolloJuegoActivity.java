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

    //TextView log;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActionBar().hide();
        setContentView(R.layout.activity_desarrollo_juego);

        frgParrilla = (ParrillaFrag) getSupportFragmentManager().findFragmentById(R.id.FrgParrilla);
        frgParrilla.setMyOnClickListener(this);


        Intent in = getIntent();

        c1 = getApplicationContext();
        et = (TextView) findViewById(R.id.text);
        gv  = (GridView) findViewById(R.id.grid_custom);
        tv = (TextView) findViewById(R.id.text_fichas);
        count = (TextView) findViewById(R.id.timer_text);

        /*Preguntarle al Luis que es i pk lo mete en fragment_detalle*/
        //log = findViewById(R.id.TxtDetalle);



        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(DesarrolloJuegoActivity.this);
        if ( prefs.getBoolean(getString(R.string.pref_timer_key),false) ) {
            timer = 1;
        }else{
            timer = 0;
        }
        alias = prefs.getString(getString(R.string.pref_alias_key), getString(R.string.pref_alias_default));
        grid_dimension = Integer.valueOf(prefs.getString(getString(R.string.pref_size_key), getString(R.string.pref_size_default)));

        playModeDecide(prefs.getString(getString(R.string.pref_playmode_key), getString(R.string.pref_playmode_default) ));
       // playModeDecide(in.getStringExtra(getString(R.string.playmode_key)));
        gv.setNumColumns(grid_dimension);


        Board board = new Board(grid_dimension);
        game = new Game(board, gameType, 0);
        //State state = State.BLACK;

        adapter = new CustomAdapter(this, game, timer, alias);

        adapter.setMyOnClickListener(this);
        gv.setAdapter(adapter);







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


            adapter = new CustomAdapter(this, game/*, et, tv*/, timer, /*count,*/ alias);
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

        //getSupportFragmentManager().putFragment(outState, "myFragmentName", frgParrilla);
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
