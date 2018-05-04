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

    @SuppressLint("SetTextI18n")
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
        String alias = b.getString("alias");
        int size = b.getInt("size");
        int duration = b.getInt("duration");
        //String numbers = b.getString("numbers");
        int haveTimer = b.getInt("havetimer");
        int win = b.getInt("win");
        int black = b.getInt("black");
        int white = b.getInt("white");
        int diferencia = b.getInt("diferencia");

        String controlTiempo = "";
        if(haveTimer == 1){
            if (25-duration == 0 ){
            controlTiempo = "Has esgotat el temps!!";
            }else{
                controlTiempo = "Han sobrat "+ (25-duration) + " segons!";
            }
        }


        switch(win){
            case 1://VICTORIA
                txtResultats.setText("Alias: "+alias+"\nMida graella: "+String.valueOf(size)
                        +"\nTemps total: "+String.valueOf(duration)
                        +" secs.\n"+"Has guanyat !! Tu "+black+"; Oponent "
                        +white+";\n"+diferencia+" caselles de diferencia!\n"+controlTiempo);
                break;
            case -1://EMPATE
                txtResultats.setText("Alias: "+alias+"\nMida graella: "+String.valueOf(size)
                        +"\nTemps total: "+String.valueOf(duration)
                        +" secs.\n" +"Heu empatat !!\n"+controlTiempo);

                break;
            case 2:
                txtResultats.setText("Alias: "+alias+"\nMida graella: "+String.valueOf(size)
                        +"\nTemps total: "+String.valueOf(duration)
                        +" secs.\n" +"Us heu quedat sense poder completar la graella … !!\nTu "+black+"; Oponent "
                        +white+";\n"+diferencia+" caselles de diferencia!\nHan quedat "+ (size*size-(white+black)) + " caselles per cobrir\n"+controlTiempo);

                break;
            case 3:
                txtResultats.setText("Alias: "+alias+"\nMida graella: "+String.valueOf(size)
                        +"\nTemps total: "+String.valueOf(duration)
                        +" secs.\n" +"Has esgotat el temps!!\nTu "+black+"; Oponent "
                        +white+";\n"+diferencia+" caselles de diferencia!\nHan quedat "+ (size*size-(white+black)) + " caselles per cobrir");

                break;
            default://DERROTA
                txtResultats.setText("Alias: "+alias+"\nMida graella: "+String.valueOf(size)
                        +"\nTemps total: "+String.valueOf(duration)
                        +" secs.\n" +"Has perdut !! Oponent "+white+"; Tu "
                        +black+";\n"+diferencia+" caselles de diferencia!"+"\n"+controlTiempo);
                break;
        }

        txtMail.requestFocus();
    }

    public void sendEmail(View view) {

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto",txtMail.getText().toString(), null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, txtDia.getText().toString()+"\n"+txtResultats.getText());
        emailIntent.putExtra(Intent.EXTRA_TEXT, ""+txtResultats.getText());
        //emailIntent.putExtra(Intent.EXTRA_EMAIL, address);
        startActivity(Intent.createChooser(emailIntent, "Send email..."));

    }

    public void newGame(View view) {
        Intent i = new Intent(this, MenuPrincipal.class);
        startActivity(i);
    }

    public void exitGame(View view) {
        this.onBackPressed();
    }
}
