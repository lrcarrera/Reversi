package com.example.radu.reversi;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.sql.SQLOutput;

public class QueryFrag extends Fragment {


    private ListView lstListado;

    private ScoreListener listener;

    private Score[] datos =
            new Score[]{
                    new Score("Persona 1", "Asunto del correo 1", "Texto del correo 1"),
                    new Score("Persona 2", "Asunto del correo 2", "Texto del correo 2"),
                    new Score("Persona 3", "Asunto del correo 3", "Texto del correo 3"),
                    new Score("Persona 4", "Asunto del correo 4", "Texto del correo 4"),
                    new Score("Persona 5", "Asunto del correo 5", "Texto del correo 5")};



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
            String[] campos = new String[]{"_id", "alias", "fecha", "resultado"};


            Cursor cursor = db.query("Partidas", campos, null, null, null, null, null);



            SimpleCursorAdapter adt = new SimpleCursorAdapter(getContext(), R.layout.list_query,
                    cursor, new String[]{"alias", "fecha","resultado"}, new int[]{R.id.val1,R.id.val2, R.id.val3 }, 0);
            lstListado = (ListView) getView().findViewById(R.id.LstListado);
            lstListado.setAdapter(adt);
            //lstListado.setAdapter(new CustomAdapterScores(this, datos));

            lstListado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                    if (listener != null) {
                        listener.onScoreSeleccionado(//PASAR ID PARA HACER CONSULTA EN EL DETALLE);
                                (SQLiteCursor) lstListado.getAdapter().getItem(pos)*/);
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
        void onScoreSeleccionado();
    }


    public void setScoreListener(ScoreListener listener) {
        this.listener=listener;
    }
}
