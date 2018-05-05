package com.example.radu.reversi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Resultados extends AppCompatActivity {

    EditText txtResultats;
    EditText txtMail;
    EditText txtDia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_resultados);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy h:mm a");
        Calendar ca = Calendar.getInstance();


        txtResultats = (EditText) findViewById(R.id.edit_log);

        txtMail = (EditText) findViewById(R.id.edit_email);

        txtDia = (EditText) findViewById(R.id.edit_dia);


        txtDia.setText(format.format(ca.getTime()));

        txtMail.setText(R.string.email_defecto);


        Intent i = getIntent();
        Bundle b = i.getExtras();
        String alias = b.getString(getString(R.string.alias_key));
        int size = b.getInt(getString(R.string.size_key));
        int duration = b.getInt(getString(R.string.duration_key));
        int haveTimer = b.getInt(getString(R.string.hastimer_key));
        int win = b.getInt(getString(R.string.win_key));
        int black = b.getInt(getString(R.string.black_key));
        int white = b.getInt(getString(R.string.white_key));
        int diferencia = b.getInt(getString(R.string.diferencia_key));

        String controlTiempo = "";
        if(haveTimer == 1){
            if (25-duration == 0 ){
            controlTiempo = getString(R.string.temps_esgotat);
            }else{
                controlTiempo = String.format(getString(R.string.temps_restant),
                        String.valueOf(25-duration));

            }
        }


        switch(win){
            case 1://VICTORIA
                txtResultats.setText(String.format(getString(R.string.victory_log), alias, String.valueOf(size)
                , String.valueOf(duration), String.valueOf(black), String.valueOf(white), String.valueOf(diferencia), controlTiempo));
                break;

            case -1://EMPATE

                txtResultats.setText(String.format(getString(R.string.draw_log), alias, String.valueOf(size)
                        , String.valueOf(duration), controlTiempo));
                break;
            case 2://BLOQUEO INTRINSECO

                txtResultats.setText(String.format(getString(R.string.block_log), alias, String.valueOf(size)
                        , String.valueOf(duration), String.valueOf(black), String.valueOf(white), String.valueOf(diferencia), String.valueOf(size*size-(white+black)) ,controlTiempo));

                break;
            case 3://TEMPS ESGOTAT

                txtResultats.setText(String.format(getString(R.string.time_log), alias, String.valueOf(size)
                        , String.valueOf(duration), String.valueOf(black), String.valueOf(white), String.valueOf(diferencia), String.valueOf(size*size-(white+black))));

                break;
            default://DERROTA
                txtResultats.setText(String.format(getString(R.string.lose_log), alias, String.valueOf(size)
                        , String.valueOf(duration), String.valueOf(white), String.valueOf(black), String.valueOf(diferencia), controlTiempo));
                break;
        }

        txtMail.requestFocus();
    }

    public void sendEmail(View view) {

       /* Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
               getString(R.string.mail_to),txtMail.getText().toString(), null));


        emailIntent.putExtra(Intent.EXTRA_SUBJECT, txtDia.getText().toString()+"\n"+txtResultats.getText());
        emailIntent.putExtra(Intent.EXTRA_TEXT, ""+txtResultats.getText());
        startActivity(Intent.createChooser(emailIntent, getString(R.string.send_email)));
        */

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType(getString(R.string.type_email));
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{getString(R.string.email_defecto)});
        i.putExtra(Intent.EXTRA_SUBJECT, txtDia.getText().toString()+" "+txtResultats.getText().toString());
        i.putExtra(Intent.EXTRA_TEXT   , txtResultats.getText().toString());

        startActivity(Intent.createChooser(i, getString(R.string.send_email)));


    }

    public void newGame(View view) {
        Intent i = new Intent(this, MenuPrincipal.class);
        startActivity(i);
    }

    public void exitGame(View view) {
        this.onBackPressed();
    }
}
