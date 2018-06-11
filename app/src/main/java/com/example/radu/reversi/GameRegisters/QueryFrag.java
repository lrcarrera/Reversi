package com.example.radu.reversi.GameRegisters;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.radu.reversi.R;

public class QueryFrag extends Fragment {


    private ListView lstListado;
    private ScoreListener listener;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listado, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        //datos
        PartidasSQLiteHelper udb = new PartidasSQLiteHelper(getContext(),"DBPartidas", null, 1);
        SQLiteDatabase db = udb.getWritableDatabase();
        if (db != null) {
            //long oid = db.insert("Partidas", null, values);
            //System.out.println(oid);
            final String[] campos = new String[]{"_id", "alias", "fecha", "tamany",
                                           "tiempo", "negras", "blancas", "empleado", "resultado","resultado"};
           /* "CREATE table Partidas"+
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "alias TEXT,"+
                    "fecha TEXT,"+
                    "tamany INTEGER,"+
                    "tiempo TEXT,"+
                    "negras INTEGER," +
                    "blancas INTEGER," +
                    "empleado INTEGER," +
                    "resultado TEXT)";*/


            final Cursor cursor = db.query("Partidas", campos, null, null, null, null, null);



            SimpleCursorAdapter adt = new SimpleCursorAdapter(getContext(), R.layout.list_query,
                    cursor, new String[]{"alias", "fecha","resultado"}, new int[]{R.id.val1,R.id.val2, R.id.val3 }, 0);
            lstListado = (ListView) getView().findViewById(R.id.LstListado);
            lstListado.setAdapter(adt);
            //lstListado.setAdapter(new CustomAdapterScores(this, datos));

            lstListado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                    if (listener != null) {
                        cursor.moveToPosition(pos);
                        //listener.onScoreSeleccionado(/*PASAR ID PARA HACER CONSULTA EN EL DETALLE);*/
                        //        (SQLiteCursor) lstListado.getAdapter().getItem(pos));
                        Score score = new Score(cursor);
                        //System.out.println("Print de puts \n" + score.toString() );
                        listener.onScoreSeleccionado(score);
                    }
                }

            });
        }
    }


    @Override
    public void onAttach(Context c) {
        super.onAttach(c);
        try {
            listener = (ScoreListener) c;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(c.toString() + " must implement onScoreListener");
        }
    }

    public interface ScoreListener{
        void onScoreSeleccionado(Score score);
    }


    public void setScoreListener(ScoreListener listener) {
        this.listener=listener;
    }
}
