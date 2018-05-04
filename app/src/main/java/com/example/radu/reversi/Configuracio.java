package com.example.radu.reversi;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Configuracio extends AppCompatActivity {

    Button comenca;
    Bundle bundle;

    int timer_checked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();

        setContentView(R.layout.activity_configuracio);


        bundle = new Bundle();
        final Intent auxIn = getIntent();
        final Intent in = new Intent(this, DesarrolloJuego.class);
        final RadioGroup radio_group = (RadioGroup) findViewById(R.id.radio_buttons);
        final EditText alias = (EditText) findViewById(R.id.edit_text);
        CheckBox check_box = (CheckBox) findViewById(R.id.check);

        comenca = (Button) findViewById(R.id.button_comenca);


        check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    timer_checked = 1;
                }else{
                    timer_checked = 0;
                }
            }
        });


        comenca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected_id = radio_group.getCheckedRadioButtonId();
                int grid_dimension = getDimensionById(selected_id);
                String txt = alias.getText().toString();

                LinearLayout toastLayout = new LinearLayout(getApplicationContext());
                toastLayout.setBackgroundColor(Color.RED);
                toastLayout.setOrientation(LinearLayout.HORIZONTAL);
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 250);
                TextView text = new TextView(getApplicationContext());
                text.setTextColor(Color.WHITE);

                if(txt.equals("")){
                    text.setText(R.string.falta_alias);
                    toastLayout.addView(text);
                    toast.setView(toastLayout);
                    toast.show();

                }else if(grid_dimension == 0){

                    text.setText(R.string.falta_dimension);
                    toastLayout.addView(text);
                    toast.setView(toastLayout);
                    toast.show();

                }else {
                    bundle.putInt("timer", timer_checked);
                    bundle.putInt("grid_dimension", grid_dimension);
                    bundle.putString("alias", txt);

                    bundle.putString("playMode", auxIn.getStringExtra("playMode"));
                    /*if (auxIn.getStringExtra("playMode").equals("MULTYPLAYER")){
                        System.out.println("Puso multyPlayer");
                        bundle.putString("playMode", auxIn.getStringExtra("playMode") );
                    } else {
                        System.out.println("Puso individual");
                        bundle.putString("playMode", auxIn.getStringExtra("playMode") );
                    }*/
                    in.putExtras(bundle);
                    startActivity(in);
                    finish();
                }
                //finish();
            }
        });
    }
/*
    void checkbox_clicked(View view){
        CheckBox ch = (CheckBox) view;
        if (ch.isChecked()) {
            timer_checked = 1;
        } else {
            timer_checked = 0;
        }
    }
*/
    public int getDimensionById(int selected) {
        System.out.println("TUSMUERTOS"+selected);
        int returner = 0;
        if (selected == 2){
            returner = 6;
        } else if (selected == 3){
            returner = 4;
        }else if (selected == 1){
            returner = 8;
        }
        return returner;
    }
}
