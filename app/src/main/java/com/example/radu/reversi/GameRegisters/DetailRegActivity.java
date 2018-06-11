package com.example.radu.reversi.GameRegisters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.radu.reversi.GameRegisters.RegFrag;
import com.example.radu.reversi.R;

public class DetailRegActivity extends AppCompatActivity {

    public static final String EXTRA_TEXTO =
            "cat.udl.eps.fragments.ejmoreflexible.EXTRA_TEXTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso_bd_detalle);



       // String name = i.getStringExtra("value");
        Score score = getIntent().getParcelableExtra("Score");
        RegFrag frag = (RegFrag) getFragmentManager().findFragmentById(R.id.FrgDetalleQuery);
        frag.mostrarDetalle(score);





    }
}
