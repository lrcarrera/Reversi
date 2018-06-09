package com.example.radu.reversi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class LogFrag extends Fragment {


    TextView txtDetalle;
    @Override

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_log, container, false);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        txtDetalle = (TextView) getView().findViewById(R.id.TxtDetalleLog);
        if (savedInstanceState != null) {
            txtDetalle.setText(savedInstanceState.getString(getString(R.string.key_log)));
        }

    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(getString(R.string.key_log), txtDetalle.getText().toString());
    }

    public void mostrarLog(String info) {
        txtDetalle = (TextView) getView().findViewById(R.id.TxtDetalleLog);
        txtDetalle.append(info);
    }


}