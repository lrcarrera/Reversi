package com.example.radu.reversi.GameDevelopment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.radu.reversi.R;


public class ParrillaFrag extends Fragment {


    private MyOnClickListener listener;



    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_parrilla, container, false);
    }

    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
    }

    @Override
    public void onAttach(Context c) {
        super.onAttach(c);
        try {
            listener = (MyOnClickListener) c;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(c.toString() + " must implement OnCorreosListener");
        }
    }

    public interface MyOnClickListener{
        void onLogSeleccionado(String info);
    }

    public void setMyOnClickListener(MyOnClickListener listener) {
        this.listener=listener;
    }



}
