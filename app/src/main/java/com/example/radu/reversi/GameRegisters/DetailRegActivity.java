package com.example.radu.reversi.GameRegisters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.radu.reversi.GameActionBar.MyActionBar;
import com.example.radu.reversi.R;

public class DetailRegActivity extends AppCompatActivity implements BddStrings {

    public static final String EXTRA_TEXTO =
            "cat.udl.eps.fragments.ejmoreflexible.EXTRA_TEXTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso_bd_detalle);
        MyActionBar.showActionBar(this, getSupportActionBar(), 4);

        Score score = getIntent().getParcelableExtra(STRING_SCORE);
        RegFrag frag = (RegFrag) getFragmentManager().findFragmentById(R.id.FrgDetalleQuery);
        frag.mostrarDetalle(score);





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
        finish();
    }
}
