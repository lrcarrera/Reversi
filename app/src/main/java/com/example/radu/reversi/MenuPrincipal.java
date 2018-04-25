package com.example.radu.reversi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuPrincipal extends AppCompatActivity {

    private Button ajuda;
    private Button comenca_partida;
    private Button sortir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);


        final Intent ajuda_in = new Intent(this, Ayuda.class);
        final Intent partida_in = new Intent(this, Configuracio.class);

        ajuda = (Button) findViewById(R.id.button_ajuda);
        ajuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ajuda_in);
            }
        });

        comenca_partida = (Button) findViewById(R.id.button_comenca_partida);
        comenca_partida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(partida_in);
                finish();
            }
        });

        sortir = (Button) findViewById(R.id.button_sortida);
        sortir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.sortir_joc, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }



}
