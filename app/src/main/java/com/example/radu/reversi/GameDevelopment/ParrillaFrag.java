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



       // et = (TextView) getView().findViewById(R.id.text);
        //tv = (TextView) getView().findViewById(R.id.text_fichas);




        //c1 = getActivity();

      /*  SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if ( prefs.getBoolean(getString(R.string.pref_timer_key),false) ) {
            timer = 1;
        }else{
            timer = 0;
        }*/
        //alias = prefs.getString(getString(R.string.pref_alias_key), getString(R.string.pref_alias_default));

       // gv.setNumColumns(grid_dimension);
        //if (state == null) {
         /*   Intent in = getActivity().getIntent();
            playModeDecide(in.getStringExtra(getString(R.string.playmode_key)));
            Board board = new Board(grid_dimension);
            game = new Game(board, gameType, 0);
*/
          /*  adapter = new CustomAdapter(getContext(), game, timer, alias);
            gv.setAdapter(adapter);*/
      /*  }
        else{
            game = (Game) state.getParcelable(getString(R.string.game_key));
            //timer = state.getInt(getString(R.string.timer_key));
            //alias = state.getString(getString(R.string.alias_key));
            //grid_dimension = state.getInt(getString(R.string.size_key));
            adapter = new CustomAdapter(getContext(), game, timer,alias);
            gv.setAdapter(adapter);
            adapter.updateNumbers();
        }*/

    }


    /*@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        adapter.stopTimerTask(count);
       // outState.putParcelable(getString(R.string.game_key), game);
    }*/


    @Override
    public void onAttach(Context c) {
        super.onAttach(c);
        try {
            listener = (MyOnClickListener) c;
            //context = c;
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
