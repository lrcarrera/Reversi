package com.example.radu.reversi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
                }
            }
        });


        comenca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected_id = radio_group.getCheckedRadioButtonId();
                int grid_dimension = getDimensionById(selected_id);

                bundle.putInt("timer", timer_checked);
                bundle.putInt("grid_dimension", grid_dimension);
                bundle.putString("alias", alias.getText().toString());
                in.putExtras(bundle);
                startActivity(in);
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
        int returner = 8;
        if (selected == 2){
            returner = 6;
        } else if (selected == 3){
            returner = 4;
        }
        return returner;
    }
}
