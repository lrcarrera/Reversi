package com.example.radu.reversi.GameRegisters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.radu.reversi.GameMenu.MenuPrincipalActivity;
import com.example.radu.reversi.GamePreferences.OpcionesActivity;
import com.example.radu.reversi.MyActionBar;
import com.example.radu.reversi.R;

public class AccessBDActivity extends AppCompatActivity implements QueryFrag.ScoreListener,
        BddStrings {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso_bd);
      // get
        MyActionBar.showActionBar(this, getSupportActionBar(), 3);
        QueryFrag frgListado = (QueryFrag)getFragmentManager().findFragmentById(R.id.FrgListado);
        frgListado.setScoreListener(this);
    }


    @Override
    public void onScoreSeleccionado(Score score) {

        boolean hayDetalle = (getFragmentManager().findFragmentById(R.id.FrgDetalleQuery) != null);

        if(hayDetalle) {
            RegFrag f1 = (RegFrag) getFragmentManager().findFragmentById(R.id.FrgDetalleQuery);
            System.out.println(score.toString());
            f1.mostrarDetalle(score);

        }
        else {
            Intent i = new Intent(this, DetailRegActivity.class);
            //i.putExtra("Score", score);
            i.putExtra(STRING_SCORE, score);
            startActivity(i);
        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle item selection
        if (item.getItemId() == android.R.id.home){
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
