package com.example.radu.reversi;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

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
    int WRONGCELL = 5;
    //int GAMEDURATION = 25;
    int firstMove = 0;
    int SECONDINMILISECONDS=1000;

    String alias, numbers;

    /*public CustomAdapter(Context context, Caller listener){
        this.context = context;

    }*/

    public CustomAdapter (Context c,  Game game, TextView et, TextView tv, int timer, TextView count, String alias){
        this.context = c;
        this.tv = tv;
        this.et = et;
        this.count = count;
        this.game = game;
        this.timer = timer;
        this.size = game.getBoard().size();
        this.alias = alias;
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

        if (convertView == null) {
            cell = new ImageButton(context);
            cell.setLayoutParams(new GridView.LayoutParams(parent.getWidth() / size, parent.getWidth() / size));
            cell.setScaleType(ImageView.ScaleType.FIT_CENTER);
            cell.setScaleType(ImageButton.ScaleType.FIT_XY);
            cell.setPadding(0, 0, 0, 0);

        } else {
            cell = (ImageButton) convertView;


        }


        cell.setTag(position);
        changeCellImage(cell, new Position(i, j));

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
                    makeToast(WRONGCELL);
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
            if (game.getGameType() == GameType.EASY){
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
            } else {
                cell.setImageResource(R.drawable.whitegreen);
            }
        }
    }

    public void isObjectiveProcess(Position p){
        if(firstMove == 0){
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
            if(game.getGameType() != GameType.MULTIPLAYER){
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
            finish();
        }
        /* Updates the EditText's of the activity*/
        updateNumbers();
    }


    public void finish(){


        updateNumbers();

        Bundle b = new Bundle();
        int win = 0;
        int diferencia = this.game.getBoard().getCountWhite() - this.game.getBoard().getCountBlack();

        if(this.game.getBoard().getCountBlack() > this.game.getBoard().getCountWhite()){
            win = 1;
            diferencia = this.game.getBoard().getCountBlack() - this.game.getBoard().getCountWhite();
        }else if(this.game.getBoard().getCountBlack() == this.game.getBoard().getCountWhite()){
            win = -1;
        }


        if (game.getGameDuration() == 25 && timer == 1){
            makeToast(TEMPUS);
            win = 3;
        } else if (((game.getBoard().getCountBlack() + game.getBoard().getCountWhite()) != getCount())){
            makeToast(BLOCK);
            win = 2;
        } else {
            if(game.getBoard().getCountBlack() > game.getBoard().getCountWhite()) {
                makeToast(WIN);
            } else if (game.getBoard().getCountBlack() < game.getBoard().getCountWhite()) {
                makeToast(LOSE);
            } else {
                makeToast(DRAW);
            }
        }

       /* if(((game.getBoard().getCountBlack() + game.getBoard().getCountWhite()) != getCount()) && game.getGameDuration() != 25){
            makeToast(BLOCK);
            win = 2;

        } else {
            if (game.getGameDuration() == 25 && timer == 1){
                makeToast(TEMPUS);
                win = 3;
            } else if(game.getBoard().getCountBlack() > game.getBoard().getCountWhite()) {
                makeToast(WIN);
            } else if (game.getBoard().getCountBlack() < game.getBoard().getCountWhite()) {
                makeToast(LOSE);
            } else {
                makeToast(DRAW);
            }
        }*/

        android.content.Intent in = new android.content.Intent(context, Resultados.class);
        b.putString("alias", this.alias);
        b.putInt("size", this.size);
        b.putInt("duration", this.game.getGameDuration());
        //b.putString("numbers", this.numbers);
        b.putInt("black", this.game.getBoard().getCountBlack());
        b.putInt("white", this.game.getBoard().getCountWhite());

        b.putInt("havetimer", this.timer);


        b.putInt("diferencia", diferencia);
        b.putInt("win", win);

        in.putExtras(b);
        context.startActivity(in);

        //((Activity)this.context).finish();

        //DesarrolloJuego d = new DesarrolloJuego();
        //d.callIntent();
        //final android.content.Intent configuracio = android.content.Intent(this, Configuracio.class);
    }

    public void countTime(){
        startTimer();
        firstMove++;
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
                            game.increaseDuration();
                            count.setText(Integer.valueOf(game.getGameDuration()).toString());
                            if (timer == 1){
                                count.setTextColor(Color.RED);
                            } else {
                                count.setTextColor(Color.BLUE);
                            }

                            if (game.getGameDuration() == 25 && timer == 1){
                                game.setState(State.FINISHED);
                                stoptimertask(count);
                                finish();
                                //updateNumbers();
                            }
                        }

                    }
                });
            }
        };
    }

    public  void updateNumbers(){
        String state, auxiliar;
        int toFill = getCount() -
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
        } else if (i == WRONGCELL){
            image.setImageResource(R.drawable.wrong_icon);
            text.setText(R.string.casella_incorrecta);
        }
        toastLayout.addView(image);
        toastLayout.addView(text);
        imageToast.setView(toastLayout);
        imageToast.setDuration(Toast.LENGTH_SHORT);
        imageToast.show();

    }


}
