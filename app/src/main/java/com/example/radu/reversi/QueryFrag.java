package com.example.radu.reversi;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ScoreListener) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnScoreListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listado, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        //datos


        lstListado = (ListView) getView().findViewById(R.id.LstListado);

        lstListado.setAdapter(new CustomAdapterScores(this, datos));

        lstListado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                if (listener!=null) {
                    listener.onScoreSeleccionado(
                            (Score)lstListado.getAdapter().getItem(pos));
                }
            }

        });
    }

    public interface ScoreListener{
        void onScoreSeleccionado(Score c);
    }

    public void setScoreListener(ScoreListener listener) {
        this.listener=listener;
    }
}