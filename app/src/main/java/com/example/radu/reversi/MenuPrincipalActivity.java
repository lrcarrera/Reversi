package com.example.radu.reversi;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.radu.reversi.GameRegisters.AccessBDActivity;


public class MenuPrincipalActivity extends AppCompatActivity {

    private Button ajuda;
    private Button comenca_partida;
    private Button sortir;
    private Button score;

    private static final int HELP = 0;
    private static final int START = 1;
    private static final int END = 2;
    private static final int CONFIG = 3;
    private static final int SCORE = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();

        setContentView(R.layout.activity_menu_principal);
        showActionBar();

        //this.deleteDatabase("DBPartidas");

        final Intent ajuda_in = new Intent(this, AyudaActivity.class);

        ajuda = (Button) findViewById(R.id.button_ajuda);
        ajuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(HELP);
            }
        });

        comenca_partida = (Button) findViewById(R.id.button_comenca_partida);
        comenca_partida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(START);
            }
        });

        sortir = (Button) findViewById(R.id.button_sortida);
        sortir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(END);
            }
        });

        score = (Button) findViewById(R.id.button_score);
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(SCORE);
            }
        });
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
            startActivities(CONFIG);
            return true;
        }
        return false;
    }

    public void startActivities(int act){
        switch (act){
            case HELP:
                final Intent ajuda_in = new Intent(this, AyudaActivity.class);
                startActivity(ajuda_in);
                break;
            case START:
                final Intent partida_in = new Intent(this, DesarrolloJuegoActivity.class);
                startActivity(partida_in);
                finish();
                break;
            case END:
                Toast.makeText(getApplicationContext(), R.string.sortir_joc, Toast.LENGTH_SHORT).show();
                finish();
                break;
            case CONFIG:
                final Intent config = new Intent(this, OpcionesActivity.class);
                startActivity(config);
                break;

            case SCORE:
                final Intent score = new Intent(this, AccessBDActivity.class);
                startActivity(score);
                break;
            default:
                break;

        }
    }



}
