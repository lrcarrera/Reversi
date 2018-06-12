package com.example.radu.reversi;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.radu.reversi.GameRegisters.BddStrings;
import com.example.radu.reversi.GameRegisters.PartidasSQLiteHelper;

public class DataAsynctask extends AsyncTask<String, Integer, Void> implements BddStrings {


    Context c1;

    public DataAsynctask(Context c1) {
        this.c1 = c1;
    }

    ProgressDialog  mProgressDialog;

    @Override
    protected Void doInBackground(String... params) {

        if(params[0].equals(c1.getString(R.string.key_add_bd))) {

            ContentValues values = new ContentValues();

            values.put(STRING_ALIAS, params[1]);
            values.put(STRING_FECHA, params[2]);
            values.put(STRING_TAMANY, Integer.valueOf(params[3]));
            values.put(STRING_TIEMPO, params[8]);
            values.put(STRING_EMPLEADO, params[4]);
            values.put(STRING_RESULTADO, params[5]);
            values.put(STRING_BLANCAS, params[7]);
            values.put(STRING_NEGRAS, params[6]);


            PartidasSQLiteHelper udb = new PartidasSQLiteHelper(c1, STRING_DBNAME,
                    null, 1);
            SQLiteDatabase db = udb.getWritableDatabase();
            if (db != null) {
                long oid = db.insert(STRING_TABLE_NAME, null, values);
                db.close();
            }
        }

        return null;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Create ProgressBar
        mProgressDialog = new ProgressDialog(c1);
        // Set your ProgressBar Title
        mProgressDialog.setTitle(c1.getString(R.string.connect_bd));
        mProgressDialog.setIcon(R.drawable.chrono_icon);
        // Set your ProgressBar Message
        mProgressDialog.setMessage(c1.getString(R.string.insert_database));
        mProgressDialog.setProgressStyle(ProgressDialog.THEME_HOLO_DARK);
        // Show ProgressBar
        mProgressDialog.setCancelable(false);
        //  mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (this.mProgressDialog.isShowing()) {
            this.mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
    }

}