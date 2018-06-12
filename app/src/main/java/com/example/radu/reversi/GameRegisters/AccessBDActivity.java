package com.example.radu.reversi.GameRegisters;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Filter;
import android.widget.Toast;

import com.example.radu.reversi.GameLogic.Position;
import com.example.radu.reversi.GameMenu.MenuPrincipalActivity;
import com.example.radu.reversi.GamePreferences.OpcionesActivity;
import com.example.radu.reversi.MyActionBar;
import com.example.radu.reversi.R;

public class AccessBDActivity extends AppCompatActivity implements QueryFrag.ScoreListener,
        BddStrings {

    QueryFrag frgListado;
    final String[] campos = new String[]{STRING_ID, STRING_ALIAS, STRING_FECHA, STRING_TAMANY,
            STRING_TIEMPO, STRING_NEGRAS, STRING_BLANCAS, STRING_EMPLEADO, STRING_RESULTADO};
    Cursor data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso_bd);
      // get
        MyActionBar.showActionBar(this, getSupportActionBar(), 3);
        frgListado = (QueryFrag)getFragmentManager().findFragmentById(R.id.FrgListado);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.dashboard, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) AccessBDActivity.this.getSystemService(Context.SEARCH_SERVICE);


        SearchView sv = null;
        if (searchItem != null) {
            sv = (SearchView) searchItem.getActionView();
        }
        if (sv != null) {
            sv.setSearchableInfo(searchManager.getSearchableInfo(AccessBDActivity.this.getComponentName()));
        }
        sv.setQueryHint(getString(R.string.cerca_alias));

        searchViewCustomListener(sv);
        return super.onCreateOptionsMenu(menu);
    }

    private void searchViewCustomListener(SearchView sv) {



        data = frgListado.getAdapterFromFragment().getCursor();

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            boolean pressed = false;
                                      @Override
                                      public boolean onQueryTextSubmit(String query) {

                                          if(!pressed) {
                                              Toast t1 = Toast.makeText(getApplicationContext(), R.string.case_sensitive, Toast.LENGTH_LONG);
                                              t1.setGravity(Gravity.CENTER, 0, 0);
                                              t1.show();
                                              pressed = true;
                                          }

                                          PartidasSQLiteHelper udb = new PartidasSQLiteHelper(getApplicationContext(),STRING_DBNAME, null, 1);
                                          SQLiteDatabase db = udb.getWritableDatabase();

                                          Cursor cursor = db.query(STRING_TABLE_NAME, campos, "alias=?", new String[]{query}, null, null, null);
                                          frgListado.getAdapterFromFragment().swapCursor(cursor);
                                          frgListado.getAdapterFromFragment().notifyDataSetChanged();

                                          return true;
                                      }

                                      @Override
                                      public boolean onQueryTextChange(String newText) {

                                          frgListado.getAdapterFromFragment().swapCursor(data);
                                          frgListado.getAdapterFromFragment().notifyDataSetChanged();
                                          return false;

                                      }

                                  }
        );

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
        //final Intent menuPrincipal = new Intent(this, MenuPrincipalActivity.class);
        //startActivity(menuPrincipal);
        finish();
    }




}
