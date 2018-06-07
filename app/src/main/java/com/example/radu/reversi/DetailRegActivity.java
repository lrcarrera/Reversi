package com.example.radu.reversi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailRegActivity extends AppCompatActivity {

    public static final String EXTRA_TEXTO =
            "cat.udl.eps.fragments.ejmoreflexible.EXTRA_TEXTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso_bd_detalle);

        Intent i = getIntent();

        String name = i.getStringExtra("value");
        RegFrag frag = (RegFrag) getFragmentManager().findFragmentById(R.id.FrgDetalle);
        frag.mostrarDetalle(getIntent().getStringExtra(EXTRA_TEXTO));





    }
}
