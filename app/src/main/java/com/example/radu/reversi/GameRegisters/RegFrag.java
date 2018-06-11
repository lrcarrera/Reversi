package com.example.radu.reversi.GameRegisters;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.radu.reversi.R;

public class RegFrag extends Fragment implements BddStrings {

    TextView alias;
    TextView alias_title;
    TextView dia_hora;
    TextView date_title;
    TextView tamany;
    TextView tamany_title;
    TextView control_tiempo;
    TextView control_title;
    TextView negras;
    TextView negras_title;
    TextView blancas;
    TextView blancas_title;
    TextView tiempo_empleado;
    TextView empleado_title;
    TextView resultado;
    TextView title;
    TextView result;
    ImageView imageView;
    CardView card1;
    CardView card2;
    Score score;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_detalle, container, false);

    }

    public void findTextViews(){
        title = (TextView) getView().findViewById(R.id.title_title);
        alias = (TextView) getView().findViewById(R.id.alias);
        dia_hora = (TextView) getView().findViewById(R.id.dia_hora);
        tamany = (TextView) getView().findViewById(R.id.tamany);
        control_tiempo = (TextView) getView().findViewById(R.id.control_tiempo);
        negras = (TextView) getView().findViewById(R.id.negras);
        blancas = (TextView) getView().findViewById(R.id.blancas);
        tiempo_empleado = (TextView) getView().findViewById(R.id.tiempo_empleado);
        resultado = (TextView) getView().findViewById(R.id.resultado);

        alias_title = (TextView) getView().findViewById(R.id.alias_title);
        date_title = (TextView) getView().findViewById(R.id.date_title);
        tamany_title = (TextView) getView().findViewById(R.id.tamany_title);
        control_title = (TextView) getView().findViewById(R.id.control_title);
        negras_title = (TextView) getView().findViewById(R.id.negras_title);
        blancas_title = (TextView) getView().findViewById(R.id.blancas_title);
        empleado_title = (TextView) getView().findViewById(R.id.empleado_title);
        result = (TextView) getView().findViewById(R.id.resultado_title);
        imageView = (ImageView) getView().findViewById(R.id.image_state);
        card1 = (CardView) getView().findViewById(R.id.car1);
        card2 = (CardView) getView().findViewById(R.id.card2);
    }

    public void  setVisibility(String control){
        title.setVisibility(View.VISIBLE);
        alias_title.setVisibility(View.VISIBLE);
        date_title.setVisibility(View.VISIBLE);
        tamany_title.setVisibility(View.VISIBLE);
        control_title.setVisibility(View.VISIBLE);
        negras_title.setVisibility(View.VISIBLE);
        blancas_title.setVisibility(View.VISIBLE);
        result.setVisibility(View.VISIBLE);
        setCardViews();
        card2.setVisibility(View.VISIBLE);
        card1.setVisibility(View.VISIBLE);
        if (control.equals(STRING_ACTIVADO)){
            empleado_title.setVisibility(View.VISIBLE);
        } else {
            empleado_title.setVisibility(View.INVISIBLE);
        }
        tiempo_empleado.setVisibility(View.VISIBLE);


    }

    public void setCardViews(){
        card2.setVisibility(View.VISIBLE);
        card1.setVisibility(View.VISIBLE);
        card1.setCardBackgroundColor(getResources().getColor(R.color.DarkSeaGreen, null));
        card2.setCardBackgroundColor(getResources().getColor(R.color.DarkSeaGreen, null));
        card2.setCardElevation(3);
    }
    public void mostrarDetalle(Score score) {
        this.score = score;
        String control = score.getControlTiempo();
        findTextViews();
        setVisibility(control);


        alias.setText(score.getAlias());
        dia_hora.setText(score.getFecha());
        tamany.setText(String.valueOf(score.getTamany()));
        control_tiempo.setText(control);
        negras.setText(String.valueOf(score.getNegras()));
        blancas.setText(String.valueOf(score.getBlancas()));
        System.out.println(control);
        System.out.println(STRING_ACTIVADO);
        if (control.equals(STRING_ACTIVADO)){
            tiempo_empleado.setText(String.valueOf(score.getEmpleado()));
        } else {
            empleado_title.setVisibility(View.INVISIBLE);
            tiempo_empleado.setVisibility(View.INVISIBLE);

        }
        resultado.setText(score.getResultado());
        setImage(score.getResultado());

    }

    public void setImage(String resultado){
        switch (resultado){
            case STRING_DERROTA:
                imageView.setImageResource(R.drawable.dislike_icon);
                break;
            case STRING_EMPATE:
                imageView.setImageResource(R.drawable.balance_icon);
                break;
            case STRING_VICTORIA:
                imageView.setImageResource(R.drawable.like_icon);
                break;
            case STRING_BLOAQUEIG:
                imageView.setImageResource(R.drawable.block_icon);
                break;
            case STRING_TEMPS:
                imageView.setImageResource(R.drawable.chrono_icon);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        if (savedInstanceState != null) {

            mostrarDetalle((Score) savedInstanceState.getParcelable(STRING_SCORE));
            //txtDetalle.setText(savedInstanceState.getString(getString(R.string.key_log)));
        }

    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(STRING_SCORE, score);
        //outState.putString(getString(R.string.key_log), txtDetalle.getText().toString());
    }

}
