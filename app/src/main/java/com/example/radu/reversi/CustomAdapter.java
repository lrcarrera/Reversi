package com.example.radu.reversi;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class CustomAdapter extends BaseAdapter {
    private final Context context;
    private Game game;
    private int size;
    private TextView et, tv, count;
    private int timer;
    Timer time;
    TimerTask timerTask;
    final Handler handler = new Handler();

    int BLOCK = 0;
    int WIN = 1;
    int LOSE = 2;
    int DRAW = 3;
    int TEMPUS = 4;
    int GAMEDURATION = 25;
    int SECONDINMILISECONDS=1000;
    int MULTIPLAYER = 1;

    public CustomAdapter (Context c, Game game, TextView et, TextView tv, int timer, TextView count){
        this.context = c;
        this.tv = tv;
        this.et = et;
        this.count = count;
        this.game = game;
        this.timer = timer;
        this.size = game.getBoard().size();
    }

    @Override
    public int getCount() {
        //return 0;
        return this.game.getBoard().totalCells();
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ImageButton cell;
        int i,j;


        i = position % size;
        j = position / size;

       // int dim = parent.getWidth()/size;
        
        if (convertView == null) {
            cell = new ImageButton(context);

           /* if(context.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
                cell.setLayoutParams(new GridView.LayoutParams(parent.getWidth() / (game.getBoard().size()*2), parent.getWidth() / (game.getBoard().size()*2)));
            }else{*/


            //}

            cell.setLayoutParams(new GridView.LayoutParams(parent.getWidth() / size, parent.getWidth() / size));
            cell.setScaleType(ImageView.ScaleType.FIT_CENTER);
            cell.setScaleType(ImageButton.ScaleType.FIT_XY);
            cell.setPadding(0, 0, 0, 0);







        } else {
            cell = (ImageButton) convertView;


        }

        changeCellImage(cell, new Position(i, j));

        cell.setTag(position);

        cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pos = (int) v.getTag();
                int i = pos % size;
                int j = pos / size;


                if(game.getBoard().isObjective(new Position(i,j))){
                    isObjectiveProcess(new Position(i,j));
                } else {
                    /*No es una casilla valida, sonido incorrecto*/
                    System.out.println("Casilla incorrecta puslada");
                }

            }
        });
        return cell;
    }

    public void changeCellImage(ImageButton cell, Position p){
        if (game.getBoard().isBlack(p)){
            cell.setImageResource(R.drawable.black);
        } else if (game.getBoard().isWhite(p)){
            cell.setImageResource(R.drawable.white);
        } else if (game.getBoard().isEmpty(p)){
            cell.setImageResource(R.drawable.green);
        } else if (game.getBoard().isObjective(p)){
            if(game.getBoard().getTransform(p)  == 1){
                cell.setImageResource(R.drawable.one_icon);
            }else if(game.getBoard().getTransform(p)  == 2){
                cell.setImageResource(R.drawable.two_icon);
            }else if(game.getBoard().getTransform(p)  == 3){
                cell.setImageResource(R.drawable.three_icon);
            }else if(game.getBoard().getTransform(p)  == 4){
                cell.setImageResource(R.drawable.four_icon);
            }else if(game.getBoard().getTransform(p)  == 5){
                cell.setImageResource(R.drawable.five_icon);
            }else if(game.getBoard().getTransform(p)  == 6){
                cell.setImageResource(R.drawable.six_icon);
            }else if(game.getBoard().getTransform(p)  == 7){
                cell.setImageResource(R.drawable.seven_icon);
            }else if(game.getBoard().getTransform(p)  == 8){
                cell.setImageResource(R.drawable.eight_icon);
            }else if(game.getBoard().getTransform(p)  == 9){
                cell.setImageResource(R.drawable.nine_icon);
            }else if(game.getBoard().getTransform(p)  == 10){
                cell.setImageResource(R.drawable.ten_icon);
            }else{
                cell.setImageResource(R.drawable.whitegreen);
            }

        }
    }

    public void isObjectiveProcess(Position p){
        if(timer == 1){
            countTime();
        }

        /*User Turn*/
        if(game.getState()==State.BLACK){
            game.move(p);
            /* Mobile objectives set*/
            game.setObjectives(size);
            notifyDataSetChanged();

        }

        /*Mobile Turn*/
        if(game.getState()==State.WHITE){

            System.out.println("ENTRO O KELOKE phone turn");
            if(MULTIPLAYER != 0){
                game.phoneTurn();
            } else {
                game.move(p);
            }
            /* Player objectives set */
            game.setObjectives(size);
            notifyDataSetChanged();

        }
        if(game.getState() == State.FINISHED){
            System.out.println("Entro en la condicion final");
            finishToast();
        }
        /* Updates the EditText's of the activity*/
        updateNumbers();
    }

    public void finishToast(){
        if(((game.getBoard().getCountBlack() + game.getBoard().getCountWhite()) != game.getBoard().size() * game.getBoard().size()) && GAMEDURATION != 0){
            makeToast(BLOCK);
        } else {
            if (GAMEDURATION == BLOCK){
                makeToast(TEMPUS);
            } else if(game.getBoard().getCountBlack() > game.getBoard().getCountWhite()) {
                makeToast(WIN);
            } else if (game.getBoard().getCountBlack() < game.getBoard().getCountWhite()) {
                makeToast(LOSE);
            } else {
                makeToast(DRAW);
            }
        }
    }

    public void countTime(){
        startTimer();
        timer=0;
    }

    public void startTimer() {
        time = new Timer();
        initializeTimerTask();
        time.schedule(timerTask, 0, SECONDINMILISECONDS); //
    }

    public void stoptimertask(View v) {
        if (time != null) {
            time.cancel();
            time = null;
        }
    }

    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        if(game.getState() == State.FINISHED){
                            stoptimertask(count);
                        } else{
                            GAMEDURATION--;
                            count.setText(Integer.valueOf(GAMEDURATION).toString());
                            count.setTextColor(Color.BLUE);
                            if (GAMEDURATION == BLOCK){
                                game.setState(State.FINISHED);
                                stoptimertask(count);
                                finishToast();
                                updateNumbers();
                            }
                        }

                    }
                });
            }
        };
    }

    public  void updateNumbers(){
        String numbers, state, auxiliar;
        int toFill = (game.getBoard().size() * game.getBoard().size()) -
                (game.getBoard().getCountBlack() + game.getBoard().getCountWhite());
        numbers = String.format(context.getString(R.string.info_caselles),
                Integer.valueOf(game.getBoard().getCountBlack()).toString(),
                Integer.valueOf(game.getBoard().getCountWhite()).toString(),
                Integer.valueOf(toFill).toString());

        tv.setText(numbers);
        if(game.getState() == State.FINISHED){
            auxiliar = context.getString(R.string.finalitzada);
        } else {
            auxiliar = context.getString(R.string.en_marxa);
        }
        state = String.format(context.getString(R.string.estat), auxiliar);
        et.setText(state);
    }


    public void makeToast(int i){

        Toast imageToast = new Toast(context);
        imageToast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastLayout = new LinearLayout(context);
        toastLayout.setOrientation(LinearLayout.HORIZONTAL);
        toastLayout.setBackgroundColor(Color.WHITE);
        ImageView image = new ImageView(context);
        TextView text = new TextView(context);

        if (i == BLOCK){
            image.setImageResource(R.drawable.block_icon);
            text.setText(R.string.bloqueo);
        } else if (i == WIN){
            //MediaPlayer ring= MediaPlayer.create(DesarrolloJuego.this, R.raw.win_sound);
            //ring.start();
            //System.out.println("Salio del ring");
            image.setImageResource(R.drawable.like_icon);
            text.setText(R.string.victoria);
        } else if (i == LOSE){
            //MediaPlayer ring= MediaPlayer.create(DesarrolloJuego.this, R.raw.win_sound);
            //ring.start();
            image.setImageResource(R.drawable.dislike_icon);
            text.setText(R.string.perdida);
        } else if (i == DRAW){
            image.setImageResource(R.drawable.balance_icon);
            text.setText(R.string.empate);
        } else if (i == TEMPUS){
            image.setImageResource(R.drawable.chrono_icon);
            text.setText(R.string.final_tiempo);
        }
        toastLayout.addView(image);
        toastLayout.addView(text);
        imageToast.setView(toastLayout);
        imageToast.setDuration(Toast.LENGTH_LONG);
        imageToast.show();

    }


}
