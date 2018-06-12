package com.example.radu.reversi.GameResult;


import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.radu.reversi.GameDevelopment.DesarrolloJuegoActivity;
import com.example.radu.reversi.GameMenu.MenuPrincipalActivity;
import com.example.radu.reversi.GameRegisters.AccessBDActivity;
import com.example.radu.reversi.GameRegisters.BddStrings;
import com.example.radu.reversi.GamePreferences.OpcionesActivity;
import com.example.radu.reversi.MyActionBar;
import com.example.radu.reversi.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ResultadosActivity extends AppCompatActivity  implements BddStrings{

    EditText txtResultats;
    EditText txtMail;
    EditText txtDia;
    private static final int SCORE = 0;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy h:mm a");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        Calendar ca = Calendar.getInstance();
        MyActionBar.showActionBar(this, getSupportActionBar(), 2);
        findTexts(format, ca);
        dataTreatment( ca, format);
        txtMail.requestFocus();
    }

    private void dataTreatment( Calendar ca, SimpleDateFormat format){
        Bundle b = getIntent().getExtras();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ResultadosActivity.this);
        String alias = sharedPreferences.getString(getString(R.string.pref_alias_key), STRING_DEFAULT_NAME);
        boolean haveTimer = sharedPreferences.getBoolean(getString(R.string.pref_timer_key), false);
        int size = Integer.parseInt(sharedPreferences.getString(getString(R.string.pref_size_key), STRING_DEFAULT_GRID));
        int duration = b.getInt(getString(R.string.duration_key));
        int win = b.getInt(getString(R.string.win_key));
        int black = b.getInt(getString(R.string.black_key));
        int white = b.getInt(getString(R.string.white_key));
        int diferencia = b.getInt(getString(R.string.diferencia_key));
        String timer;
        String win_to_string;

        if (haveTimer){
            timer = STRING_ACTIVADO;
        }else{
            duration = 25-duration;
            timer = STRING_DESACTIVADO;
        }

        String controlTiempo = "";
        if(haveTimer){
            if (25-duration == 0 ){
                controlTiempo = getString(R.string.temps_esgotat);
            }else{
                controlTiempo = String.format(getString(R.string.temps_restant),
                        String.valueOf(25-duration));

            }
        }

        switch(win){
            case 1://VICTORIA
                win_to_string = STRING_VICTORIA;
                txtResultats.setText(String.format(getString(R.string.victory_log), alias, String.valueOf(size)
                        , String.valueOf(duration), String.valueOf(black), String.valueOf(white), String.valueOf(diferencia), controlTiempo));
                break;

            case -1://EMPATE
                win_to_string = STRING_EMPATE;
                txtResultats.setText(String.format(getString(R.string.draw_log), alias, String.valueOf(size)
                        , String.valueOf(duration), controlTiempo));
                break;
            case 2://BLOQUEO INTRINSECO
                win_to_string = STRING_BLOAQUEIG;
                txtResultats.setText(String.format(getString(R.string.block_log), alias, String.valueOf(size)
                        , String.valueOf(duration), String.valueOf(black), String.valueOf(white), String.valueOf(diferencia), String.valueOf(size*size-(white+black)) ,controlTiempo));

                break;
            case 3://TEMPS ESGOTAT
                win_to_string = STRING_TEMPS;
                txtResultats.setText(String.format(getString(R.string.time_log), alias, String.valueOf(size)
                        , String.valueOf(duration), String.valueOf(black), String.valueOf(white), String.valueOf(diferencia), String.valueOf(size*size-(white+black))));

                break;
            default://DERROTA
                win_to_string = STRING_DERROTA;
                txtResultats.setText(String.format(getString(R.string.lose_log), alias, String.valueOf(size)
                        , String.valueOf(duration), String.valueOf(white), String.valueOf(black), String.valueOf(diferencia), controlTiempo));
                break;
        }

        insertionBdBackground(alias, ca, size, duration, win_to_string, black, white, timer );
    }

    private void insertionBdBackground(String alias, Calendar ca, int size, int duration, String win_to_string, int black, int white, String timer) {
        DataAsynctask background = new DataAsynctask(this);

        background.execute(getString(R.string.key_add_bd),alias, format.format(ca.getTime()), String.valueOf(size),
                String.valueOf(duration), win_to_string, String.valueOf(black), String.valueOf(white), timer);
    }

    private void findTexts(SimpleDateFormat format,  Calendar ca){
        txtResultats = (EditText) findViewById(R.id.edit_log);
        txtMail = (EditText) findViewById(R.id.edit_email);
        txtDia = (EditText) findViewById(R.id.edit_dia);
        txtDia.setText(format.format(ca.getTime()));
        txtMail.setText(R.string.email_defecto);
    }

    public void sendEmail(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType(getString(R.string.type_email));
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{txtMail.getText().toString()});
        i.putExtra(Intent.EXTRA_SUBJECT, txtDia.getText().toString()+" "+ getString(R.string.asunto_correo));
        i.putExtra(Intent.EXTRA_TEXT   , txtResultats.getText().toString());
        startActivity(Intent.createChooser(i, getString(R.string.send_email)));
    }

    public void newGame(View view) {
        Intent i = new Intent(this, DesarrolloJuegoActivity.class);
        startActivity(i);
        finish();
    }

    public void exitGame(View view) {
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        menu.add (Menu.NONE, SCORE, Menu.NONE, STRING_SCORES);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle item selection
        if (item.getItemId() == R.id.config_item) {
            final Intent config = new Intent(this, OpcionesActivity.class);
            startActivity(config);
            return true;
        } else if (item.getItemId() == SCORE) {
            final Intent score = new Intent(this, AccessBDActivity.class);
            startActivity(score);
            return true;
        } else if (item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        final Intent menuPrincipal = new Intent(this, MenuPrincipalActivity.class);
        startActivity(menuPrincipal);
        finish();
    }
}
