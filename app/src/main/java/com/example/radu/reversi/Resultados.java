package com.example.radu.reversi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Resultados extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_resultados);


        EditText txtResultats = (EditText) findViewById(R.id.edit_log);

        EditText txtMail = (EditText) findViewById(R.id.edit_email);

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
}
