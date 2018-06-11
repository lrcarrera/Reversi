package com.example.radu.reversi;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RegFrag extends Fragment {

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
        if (control.equals("Activado"))
            empleado_title.setVisibility(View.VISIBLE);

    }
    public void mostrarDetalle(Bundle b) {
        String control = b.getString("control_tiempo");
        findTextViews();
        setVisibility(control);


        alias.setText(b.getString("alias"));
        dia_hora.setText(b.getString("fecha"));
        tamany.setText(String.valueOf(b.getInt("tamany")));
        control_tiempo.setText(control);
        negras.setText(String.valueOf(b.getInt("negras")));
        blancas.setText(String.valueOf(b.getInt("blancas")));
        if (control.equals("Activado")) {
            tiempo_empleado.setText(String.valueOf(b.getInt("empleado")));
        } else {
            tiempo_empleado.setVisibility(View.INVISIBLE);
        }
        resultado.setText(b.get("resultado").toString());

        imageView.setImageResource(R.drawable.like_icon);



       /* bundle.putString("alias", c.getString(0));
        bundle.putString("fecha", c.getString(1));
        bundle.putInt("tamany", c.getInt(2));
        bundle.putString("control_tiempo", c.getString(3));
        bundle.putInt("negras", c.getInt(4));
        bundle.putInt("blancas", c.getInt(5));
        bundle.putInt("empleado", c.getInt(6));
        bundle.putInt("resultado", c.getInt(7));*/
    }

}
