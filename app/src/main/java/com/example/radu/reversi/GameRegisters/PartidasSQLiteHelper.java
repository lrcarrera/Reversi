package com.example.radu.reversi.GameRegisters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PartidasSQLiteHelper extends SQLiteOpenHelper {
    private static final String SQL = "CREATE table Partidas"+
                                      "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                      "alias TEXT,"+
                                      "fecha TEXT,"+
                                      "tamany INTEGER,"+
                                      "tiempo TEXT,"+
                                      "negras INTEGER," +
                                      "blancas INTEGER," +
                                      "empleado INTEGER," +
                                      "resultado TEXT)";


 /*Si control del tiempo: tiempo total empleado
 Resultado de la partida (Victoria, Derrota, Empate, Bloqueo, Tiempo
            agotado)*/

    public PartidasSQLiteHelper(Context context, String name,
                                SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL);
    }
}
