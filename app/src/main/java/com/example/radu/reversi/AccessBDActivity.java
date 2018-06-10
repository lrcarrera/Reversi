package com.example.radu.reversi;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

public class AccessBDActivity extends FragmentActivity implements QueryFrag.ScoreListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso_bd);

        QueryFrag frgListado = (QueryFrag)getFragmentManager().findFragmentById(R.id.FrgListado);
        frgListado.setScoreListener(this);



    }


    @Override
    public void onScoreSeleccionado(Bundle b) {

        boolean hayDetalle = (getFragmentManager().findFragmentById(R.id.FrgDetalleQuery) != null);

        if(hayDetalle) {
            RegFrag f1 = (RegFrag) getFragmentManager().findFragmentById(R.id.FrgDetalleQuery);
            f1.mostrarDetalle(b);

        }
        else {
            Intent i = new Intent(this, DetailRegActivity.class);
            i.putExtra(DetailRegActivity.EXTRA_TEXTO, b);
            startActivity(i);
        }


    }


}
