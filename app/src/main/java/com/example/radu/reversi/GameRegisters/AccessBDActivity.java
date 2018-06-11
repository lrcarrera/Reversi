package com.example.radu.reversi.GameRegisters;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.radu.reversi.R;

public class AccessBDActivity extends FragmentActivity implements QueryFrag.ScoreListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso_bd);

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
            i.putExtra("Score", score);
            startActivity(i);
        }


    }


}
