package com.example.radu.reversi;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.prefs.Preferences;

public class ResultadosActivity extends AppCompatActivity {

    EditText txtResultats;
    EditText txtMail;
    EditText txtDia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_resultados);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy h:mm a");
        Calendar ca = Calendar.getInstance();
        showActionBar();


        txtResultats = (EditText) findViewById(R.id.edit_log);

        txtMail = (EditText) findViewById(R.id.edit_email);

        txtDia = (EditText) findViewById(R.id.edit_dia);


        txtDia.setText(format.format(ca.getTime()));

        txtMail.setText(R.string.email_defecto);


        Intent i = getIntent();
        Bundle b = i.getExtras();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ResultadosActivity.this);
        String alias = sharedPreferences.getString(getString(R.string.pref_alias_key), "Juanjo");
        boolean haveTimer = sharedPreferences.getBoolean(getString(R.string.pref_timer_key), false);
        int size = Integer.parseInt(sharedPreferences.getString(getString(R.string.pref_size_key), "4"));
        //String alias = b.getString(getString(R.string.alias_key));
        //int size = b.getInt(getString(R.string.size_key));
        int duration = b.getInt(getString(R.string.duration_key));
        //int haveTimer = b.getInt(getString(R.string.hastimer_key));
        int win = b.getInt(getString(R.string.win_key));
        int black = b.getInt(getString(R.string.black_key));
        int white = b.getInt(getString(R.string.white_key));
        int diferencia = b.getInt(getString(R.string.diferencia_key));

        ContentValues values = new ContentValues();
        values.put("alias", alias);
        values.put("fecha",  format.format(ca.getTime()));
        values.put("tamany", size);
        if (!haveTimer){
            values.put("tiempo", "Activado");
        }else{
            values.put("tiempo", "Desactivado");
        }
        values.put("negras", black);
        values.put("blancas", white);
        if (!haveTimer){
            values.put("empleado", duration);
        } else {
            values.put("empleado", 25-duration);
        }
        values.put("resultado", "victoria");

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
                values.put("resultado", "Victoria");
                txtResultats.setText(String.format(getString(R.string.victory_log), alias, String.valueOf(size)
                , String.valueOf(duration), String.valueOf(black), String.valueOf(white), String.valueOf(diferencia), controlTiempo));
                break;

            case -1://EMPATE
                values.put("resultado", "Empate");
                txtResultats.setText(String.format(getString(R.string.draw_log), alias, String.valueOf(size)
                        , String.valueOf(duration), controlTiempo));
                break;
            case 2://BLOQUEO INTRINSECO
                values.put("resultado", "Bloqueig");
                txtResultats.setText(String.format(getString(R.string.block_log), alias, String.valueOf(size)
                        , String.valueOf(duration), String.valueOf(black), String.valueOf(white), String.valueOf(diferencia), String.valueOf(size*size-(white+black)) ,controlTiempo));

                break;
            case 3://TEMPS ESGOTAT
                values.put("resultado", "Temps Esgotat");
                txtResultats.setText(String.format(getString(R.string.time_log), alias, String.valueOf(size)
                        , String.valueOf(duration), String.valueOf(black), String.valueOf(white), String.valueOf(diferencia), String.valueOf(size*size-(white+black))));

                break;
            default://DERROTA
                values.put("resultado", "Derrota");
                txtResultats.setText(String.format(getString(R.string.lose_log), alias, String.valueOf(size)
                        , String.valueOf(duration), String.valueOf(white), String.valueOf(black), String.valueOf(diferencia), controlTiempo));
                break;
        }
        add_game_to_bd(values);
        txtMail.requestFocus();
    }

    public void add_game_to_bd(ContentValues values){
        PartidasSQLiteHelper udb = new PartidasSQLiteHelper(this,"DBPartidas",
                null, 1);
        SQLiteDatabase db = udb.getWritableDatabase();
        if (db != null){
            long oid = db.insert("Partidas", null, values);
            System.out.println(oid);
        }
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
        Intent i = new Intent(this, PlayModeActivity.class);
        startActivity(i);
        finish();
    }

    public void exitGame(View view) {
        finish();
    }

    public void showActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle ("Reversi");
        //R.color.DarkSeaGreen
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark, getTheme())));
        actionBar.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle item selection
        if (item.getItemId() == R.id.config_item) {
            //startActivities(CONFIG);
            final Intent config = new Intent(this, OpcionesActivity.class);
            startActivity(config);
            return true;
        }
        return false;
    }
}
