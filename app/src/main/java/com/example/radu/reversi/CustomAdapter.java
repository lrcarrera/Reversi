package com.example.radu.reversi;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
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

import java.util.concurrent.TimeUnit;

public class CustomAdapter extends BaseAdapter {
    private final Context context;
    private Game game;
    private int size;
    private TextView tv;
    int BLOCK = 0;
    int WIN = 1;
    int LOSE = 2;
    int DRAW = 3;
    int TIMER = 4;
    int MULTIPLAYER = 1;

    public CustomAdapter (Context c, Game game, TextView tv){
        this.context = c;
        this.tv = tv;
        this.game = game;
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

        this.game.getBoard().countAll(this.size);

        i = position % size;
        j = position / size;
        
        if (convertView == null) {
            cell = new ImageButton(context);


            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                //Do some stuff
              //  cell.setLayoutParams(new GridView.LayoutParams((parent.getWidth()/2) / game.getBoard().size(), (parent.getWidth()/2) / game.getBoard().size()));
               // cell.setScaleType(ImageView.ScaleType.FIT_CENTER);
               // cell.setPadding(0, 0, 0, 0);


                System.out.println("CUANTO ES WIDTH:"+parent.getWidth()+"HEIGH:"+parent.getHeight());
                cell.setLayoutParams(new GridView.LayoutParams(parent.getWidth() / (game.getBoard().size()*2), parent.getWidth() / (game.getBoard().size()*2)));
                cell.setScaleType(ImageButton.ScaleType.FIT_XY);
                cell.setScaleType(ImageView.ScaleType.FIT_CENTER);
                cell.setPadding(0, 0, 0, 0);
            } else{
                cell.setLayoutParams(new GridView.LayoutParams(parent.getWidth() / game.getBoard().size(), parent.getWidth() / game.getBoard().size()));

                cell.setScaleType(ImageView.ScaleType.FIT_CENTER);
                cell.setScaleType(ImageButton.ScaleType.FIT_XY);
                cell.setPadding(0, 0, 0, 0);

            }





        } else {
            cell = (ImageButton) convertView;
           /* cell.setLayoutParams(new GridView.LayoutParams((parent.getWidth() / game.getBoard().size()) - 5, (parent.getWidth() / game.getBoard().size()) - 5 ));

            float q = (float) 0.4;
            cell.setScaleX(q);
            cell.setScaleY(q);
            cell.setScaleType(ImageView.ScaleType.FIT_CENTER);
            cell.setScaleType(ImageButton.ScaleType.FIT_XY);
            cell.setPadding(0, 0, 0, 0);*/

        }











        if (game.getBoard().isBlack(new Position(i,j))){
            cell.setImageResource(R.drawable.black);
        } else if (game.getBoard().isWhite(new Position(i,j))){
            cell.setImageResource(R.drawable.white);
        } else if (game.getBoard().isEmpty(new Position(i,j))){
            cell.setImageResource(R.drawable.green);
        } else if (game.getBoard().isObjective(new Position(i,j))){

            if(game.getBoard().getTransform(new Position(i,j))  == 1){
                cell.setImageResource(R.drawable.one_icon);
            }else if(game.getBoard().getTransform(new Position(i,j))  == 2){
                cell.setImageResource(R.drawable.two_icon);
            }else if(game.getBoard().getTransform(new Position(i,j))  == 3){
                cell.setImageResource(R.drawable.three_icon);
            }else if(game.getBoard().getTransform(new Position(i,j))  == 4){
                cell.setImageResource(R.drawable.four_icon);
            }else if(game.getBoard().getTransform(new Position(i,j))  == 5){
                cell.setImageResource(R.drawable.five_icon);
            }else if(game.getBoard().getTransform(new Position(i,j))  == 6){
                cell.setImageResource(R.drawable.six_icon);
            }else if(game.getBoard().getTransform(new Position(i,j))  == 7){
                cell.setImageResource(R.drawable.seven_icon);
            }else if(game.getBoard().getTransform(new Position(i,j))  == 8){
                cell.setImageResource(R.drawable.eight_icon);
            }else if(game.getBoard().getTransform(new Position(i,j))  == 9){
                cell.setImageResource(R.drawable.nine_icon);
            }else if(game.getBoard().getTransform(new Position(i,j))  == 10){
                cell.setImageResource(R.drawable.ten_icon);
            }else{
                cell.setImageResource(R.drawable.whitegreen);
            }

        }

        cell.setTag(position);

        cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pos = (int) v.getTag();
                int i = pos % size;
                int j = pos / size;


                if(game.getBoard().isObjective(new Position(i,j))){


                    // System.out.println("CUANTASNEGRAS: "+ game.getBoard().getCountBlack());
                    if(game.getState()==State.BLACK){
                        System.out.println("ENTRO EN EL BLACK");
                        game.move(new Position(i,j));
                        game.setObjectives(size);
                        //game.getBoard().countAll(size);
                        notifyDataSetChanged();
                        /*try{
                            TimeUnit.SECONDS.sleep(2);
                        } catch(InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }*/
                    }
                    //TURNO MAKINA
                    if(game.getState()==State.WHITE){

                        System.out.println("ENTRO O KELOKE phone turn");
                        if(MULTIPLAYER != 0){
                            game.phoneTurn();
                        } else {
                            game.move(new Position(i,j));
                        }
                        //game.phoneTurn();
                        //MARCA OBJETIVOS PARA EL PLAYER
                        game.setObjectives(size);

                        notifyDataSetChanged();
                       // gnew.notifyDataSetChanged();
                        //gv.setAdapter(gnew);

                    }
                    System.out.println("Salio del turno makina");
                    //game.comprove();
                    updateNumbers();
                    /*if(context.getApplicationContext() instanceof DesarrolloJuego){
                        System.out.println("Entro en mi verga iff");
                        ((DesarrolloJuego) context).updateNumbers(game);
                    }*/

                    if(game.getState() == State.FINISHED){
                        System.out.println("Entro en la condicion final");
                        game.getBoard().countAll(size);

                        if((game.getBoard().getCountBlack() + game.getBoard().getCountWhite()) != game.getBoard().size() * game.getBoard().size()){
                            makeToast(BLOCK);
                        } else {
                            if(game.getBoard().getCountBlack() > game.getBoard().getCountWhite()) {
                                makeToast(WIN);
                            } else if (game.getBoard().getCountBlack() < game.getBoard().getCountWhite()) {
                                makeToast(LOSE);
                            } else {
                                makeToast(DRAW);
                            }
                        }

                        /*Controlar tiempo*/
                        /*if(){
                             Toast.makeText(DesarrolloJuego.this, R.string.final_tiempo, Toast.LENGTH_SHORT).show();
                        }*/


                    }
                } else {
                    /*No es una casilla valida, sonido incorrecto*/
                    System.out.println("Casilla incorrecta puslada");
                }

            }
        });
        return cell;
    }

    public  void updateNumbers(){
        System.out.println("ENtro en la casaaaaaa");
        String message;
        int toFill = (game.getBoard().size() * game.getBoard().size()) -
                (game.getBoard().getCountBlack() + game.getBoard().getCountWhite());
        message = String.format(context.getString(R.string.info_caselles),
                Integer.valueOf(game.getBoard().getCountBlack()).toString(),
                Integer.valueOf(game.getBoard().getCountWhite()).toString(),
                Integer.valueOf(toFill).toString());

        tv.setText(message);
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
        } else if (i == TIMER){
            image.setImageResource(R.drawable.chrono_icon);
            text.setText(R.string.final_tiempo);
        }
        toastLayout.addView(image);
        toastLayout.addView(text);
        imageToast.setView(toastLayout);
        imageToast.setDuration(Toast.LENGTH_LONG);
        imageToast.show();

    }
/*
    public void UpdateGame(Game game) {
        this.game = game;
        notifyDataSetChanged();
    }*/

}
