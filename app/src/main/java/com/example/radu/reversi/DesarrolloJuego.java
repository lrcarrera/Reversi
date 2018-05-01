package com.example.radu.reversi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



public class DesarrolloJuego extends AppCompatActivity {

    Board board;
    Game game;
    State state;

    GridView gv;
    CustomAdapter adapter;

    int BLOCK = 0;
    int WIN = 1;
    int LOSE = 2;
    int DRAW = 3;
    int TIMER = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desarrollo_juego);

        TextView et = (TextView) findViewById(R.id.text);
        Intent in = getIntent();
        int timer = in.getIntExtra("timer", 0 );
        final int grid_dimension = in.getIntExtra("grid_dimension", 0 );
        String alias = in.getStringExtra("alias");
        et.setText(alias + "  " + timer + "  " + grid_dimension);
        gv  = (GridView) findViewById(R.id.grid_custom);
        gv.setNumColumns(grid_dimension);


        board = new Board(grid_dimension);
        game = new Game(board);
        state = State.BLACK;


        adapter =  new CustomAdapter(this, game);


        gv.setAdapter(adapter);


        /*for (int x = 0; x < grid_dimension; x++) {
            for (int y = 0; y < grid_dimension; y++) {
                if (game.getBoard().isObjective(new Position(x, y))) {
                    count += 1;

                }
            }
        }
        System.out.println("HAY "+count+" OBJECTIVOS");
        count=0;*/



        //    imageButton.setOnClickListener(new CelaListener(context,new Position(x,y),this.board[x][y], joc));

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int i = position % game.getBoard().size();
                int j = position / game.getBoard().size();




                if(game.getBoard().isObjective(new Position(i,j))){


                   // System.out.println("CUANTASNEGRAS: "+ game.getBoard().getCountBlack());
                    if(game.getState()==State.BLACK){
                        System.out.println("ENTRO EN EL BLACK");
                        game.move(new Position(i,j));
                        game.setObjectives(grid_dimension);
                        game.getBoard().countAll(grid_dimension);
                    }

                    //MARCA OBJETIVOS PARA LA MAQUINA
                    /*game.setObjectives(grid_dimension);
                    // System.out.println("CUANTASNEGRAS: "+ game.getBoard().getCountBlack());

                    game.getBoard().countAll(grid_dimension);*/

                    CustomAdapter gnew = (CustomAdapter) parent.getAdapter();
                    gnew.notifyDataSetChanged();
                    gv.setAdapter(gnew);

                    //adapter.UpdateGame(game);
                    //gv.setAdapter(adapter);
                    //TURNO MAKINA
                    if(game.getState()==State.WHITE){
                        System.out.println("ENTRO O KELOKE phone turn");
                        game.phoneTurn();
                        //MARCA OBJETIVOS PARA EL PLAYER
                        game.setObjectives(grid_dimension);

                        gnew.notifyDataSetChanged();
                        gv.setAdapter(gnew);

                    }
                    System.out.println("Salio del turno makina");

                    if(game.getState() == State.FINISHED){
                        System.out.println("Entro en la condicion final");
                        game.getBoard().countAll(grid_dimension);

                        if(game.blockFinished()){
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




/*
                    if(game.getBoard().isObjective(new Position(0, 1))){
                        System.out.println("objetivo MECAGOENTODO\n\n\n");

                    }else{
                        System.out.println("ALFINNNN");
                    }
*/


                   // System.out.println("Llego y salio del phonwTurn");

                   /* for (int x = 0; x < grid_dimension; x++){
                        for (int y = 0; y < grid_dimension; y++){
                            game.getBoard().getCountBlack();
                            game.getBoard().getCountWhite();
                        }
                    })*/

                    //adapter.notifyDataSetChanged();

                    //adapter.UpdateGame(game);

                    //gv.setAdapter(adapter);
                    //updateAdapter();
                }


                //gv.setAdapter(adapter);
                //adapter.notifyDataSetChanged();
                //gv.setAdapter(adapter);
                //adapter.getItem(position).setLiked(True);
                //adapter.notifyDataSetChanged();
                //gv.setAdapter(adapter);


            }
        });


    }

    private void updateAdapter(){
        Toast.makeText(this,String.valueOf(game.getBoard().getCountBlack()), Toast.LENGTH_LONG).show();

    }

    public void makeToast(int i){

        Toast imageToast = new Toast(getBaseContext());
        LinearLayout toastLayout = new LinearLayout(getBaseContext());
        toastLayout.setOrientation(LinearLayout.HORIZONTAL);
        ImageView image = new ImageView(getBaseContext());
        TextView text = new TextView(getBaseContext());

        if (i == BLOCK){

            image.setImageResource(R.drawable.block_icon);
            text.setText(R.string.bloqueo);
        } else if (i == WIN){
            image.setImageResource(R.drawable.like_icon);
            text.setText(R.string.victoria);
        } else if (i == LOSE){
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
        imageToast.setDuration(Toast.LENGTH_SHORT);
        imageToast.show();


    }
}
