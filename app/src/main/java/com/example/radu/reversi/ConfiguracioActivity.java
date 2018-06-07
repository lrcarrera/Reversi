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

public class ConfiguracioActivity extends AppCompatActivity {

    Button comenca;
    Bundle bundle;

    int timer_checked = 0;
    int grid_dimension;

    private static final int RB8_ID = 1008;//first radio button id
    private static final int RB6_ID = 1006;//second radio button id
    private static final int RB4_ID = 1004;//third radio button id


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();

        setContentView(R.layout.activity_configuracio);


        bundle = new Bundle();
        final Intent auxIn = getIntent();
        final Intent in = new Intent(this, DesarrolloJuegoActivity.class);
        final RadioGroup radio_group = (RadioGroup) findViewById(R.id.radio_buttons);
        final EditText alias = (EditText) findViewById(R.id.edit_text);
        CheckBox check_box = (CheckBox) findViewById(R.id.check);

        RadioButton r8 = (RadioButton) findViewById(R.id.radio8);
        RadioButton r6 = (RadioButton) findViewById(R.id.radio6);
        RadioButton r4 = (RadioButton) findViewById(R.id.radio4);

        r8.setId(RB8_ID);
        r6.setId(RB6_ID);
        r4.setId(RB4_ID);


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
                grid_dimension = getDimensionById(selected_id);
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
                    bundle.putInt(getString(R.string.timer_key), timer_checked);
                    bundle.putInt(getString(R.string.size_key), grid_dimension);
                    bundle.putString(getString(R.string.alias_key), txt);
                    bundle.putString(getString(R.string.playmode_key), auxIn.getStringExtra(getString(R.string.playmode_key)));

                    in.putExtras(bundle);
                    startActivity(in);
                    finish();
                }
            }
        });
    }

    public int getDimensionById(int selected) {
        int returner = 0;
        if (selected == RB6_ID){
            returner = 6;
        } else if (selected == RB4_ID){
            returner = 4;
        }else if (selected == RB8_ID){
            returner = 8;
        }
        return returner;
    }
}
