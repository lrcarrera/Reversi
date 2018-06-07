package com.example.radu.reversi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class ParrillaFrag extends Fragment {


    private CorreosListener listener;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_listado, container, false);
    }

    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
/*
        lstListado = (ListView) getView().findViewById(R.id.LstListado);

        lstListado.setAdapter(new AdaptadorCorreos(this, datos));

        lstListado.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                if (listener!=null) {
                    listener.onCorreoSeleccionado(
                            (Correo)lstListado.getAdapter().getItem(pos));
                }
            }
        });*/

    }


    @Override
    public void onAttach(Context c) {
        super.onAttach(c);
        try {
            listener = (CorreosListener) c;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(c.toString() + " must implement OnCorreosListener");
        }
    }

    public interface CorreosListener{
        void onCorreoSeleccionado(Score c);
    }

    public void setCorreosListener(CorreosListener listener) {
        this.listener=listener;
    }

}
