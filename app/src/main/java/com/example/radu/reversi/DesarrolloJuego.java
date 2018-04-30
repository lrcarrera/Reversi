package com.example.radu.reversi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class DesarrolloJuego extends AppCompatActivity {

    Board board;
    Game game;
    State state;

    GridView gv;
    CustomAdapter adapter;
    int count = 0;


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

                    game.move(new Position(i,j));
                   // System.out.println("CUANTASNEGRAS: "+ game.getBoard().getCountBlack());


                    game.setObjectives(grid_dimension);
                   // System.out.println("CUANTASNEGRAS: "+ game.getBoard().getCountBlack());

                    game.getBoard().countAll(grid_dimension);

                    CustomAdapter gnew = (CustomAdapter) parent.getAdapter();
                    gnew.notifyDataSetChanged();
                    gv.setAdapter(gnew);

                    //adapter.UpdateGame(game);
                    //gv.setAdapter(adapter);

                    Toast.makeText(DesarrolloJuego.this, String.valueOf(game.getBoard().getCountBlack()),
                            Toast.LENGTH_SHORT).show();


                    if(game.getState()==State.WHITE){
                        System.out.println("ENTRO O KELOKE");
                        game.phoneTurn();
                        game.setObjectives(grid_dimension);

                        gnew.notifyDataSetChanged();
                        gv.setAdapter(gnew);

                    }

                     if(game.getState() == State.WHITE){
            System.out.println("ESPUTOBLANCO");
        }else if(game.getState() == State.BLACK){
            System.out.println("ESPUTONEGRO");

        }else if(game.getState() == State.FINISHED){
            System.out.println("esputoFINISHED!");

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
}
