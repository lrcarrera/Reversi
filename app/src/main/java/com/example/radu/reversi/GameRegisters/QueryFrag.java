package com.example.radu.reversi.GameRegisters;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.view.MenuInflater;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.radu.reversi.R;

import java.sql.SQLOutput;

public class QueryFrag extends Fragment implements BddStrings {

    String auxPosition, auxFecha;
    ListView lstListado;
    ScoreListener listener;
    ActionMode mActionMode;
    ShareActionProvider mShareActionProvider;
    SimpleCursorAdapter adt;
    SQLiteDatabase db;
    Cursor cursor;

    final String[] campos = new String[]{STRING_ID, STRING_ALIAS, STRING_FECHA, STRING_TAMANY,
            STRING_TIEMPO, STRING_NEGRAS, STRING_BLANCAS, STRING_EMPLEADO, STRING_RESULTADO};

    /*private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
    private Intent createShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");

        //String aux = getListAdapter().getItem(position).toString();
        shareIntent.putExtra(Intent.EXTRA_TEXT,"Has Seleccionado "+auxFecha);
        return shareIntent;
    }*/


    ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            getActivity().getMenuInflater().inflate(R.menu.menu_contextual, menu);

            /*MenuItem item = menu.findItem(R.id.action_share);
            mShareActionProvider = (ShareActionProvider) item.getActionProvider();
            setShareIntent(createShareIntent());
*/
            return true;
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {

                case R.id.CtxLblOpc1:
                   // Toast.makeText(getActivity().getApplicationContext(), "Item SHARE !!", Toast.LENGTH_LONG).show();
                    sendEmail();
                    mode.finish();
                    return true;


                case R.id.CtxLblOpc2:

                    //db.delete(STRING_TABLE_NAME, "_id==?", new String [] {auxPosition});
                    modifyAlias();
                    mode.finish();
                   // ContentValues content = new ContentValues();
                /*    content.put(STRING_ALIAS, auxAlias);
                    db.update(STRING_TABLE_NAME,);
                    cursor = db.query(STRING_TABLE_NAME, campos, null, null, null, null, null);
                    adt.swapCursor(cursor);
                    adt.notifyDataSetChanged();
                    Toast.makeText(getActivity().getApplicationContext(), "Item con fecha:" + auxFecha + " borrado.", Toast.LENGTH_LONG).show();
                    mode.finish();*/
                    return true;
                case R.id.CtxLblOpc3:
                    deleteRow();
                    Toast.makeText(getActivity().getApplicationContext(), "Item con fecha:" + auxFecha + " borrado.", Toast.LENGTH_LONG).show();
                    mode.finish();
                    return true;

                default:
                    return false;
            }
        }

        // Called when the user exits the action mode
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };

    private void deleteRow(){
        db.delete(STRING_TABLE_NAME, "_id==?", new String [] {auxPosition});
        cursor = db.query(STRING_TABLE_NAME, campos, null, null, null, null, null);
        adt.swapCursor(cursor);
        adt.notifyDataSetChanged();

    }

    private void sendEmail(){

        Intent emailFeedback = new Intent(Intent.ACTION_SEND);

        emailFeedback.setType("text/email");

        String body = generateSubjectEmail();
        emailFeedback.putExtra(Intent.EXTRA_EMAIL, new String[] {"pepe@pepe.es"});
        emailFeedback.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.resultat_partida_correo));
        emailFeedback.putExtra(Intent.EXTRA_TEXT   , body);
        startActivity(Intent.createChooser(emailFeedback, getString(R.string.resultat_partida_email)));


    }
    private String generateSubjectEmail(){
        cursor = db.query(STRING_TABLE_NAME, campos, "_id=?", new String[] {auxPosition}, null, null, null);

        System.out.println("AYUDA"+ cursor.getColumnCount());
        cursor.moveToFirst();
        String alias = cursor.getString(1);
        String fecha = cursor.getString(2);
        String tamaño = String.valueOf(cursor.getInt(3));
        String tiempo = cursor.getString(4);
        String negras = String.valueOf(cursor.getInt(5));

        String blancas = String.valueOf(cursor.getInt(6));
        String empleado = String.valueOf(cursor.getInt(7));
        String resultado = cursor.getString(8);

        return new String(String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s", alias, fecha, tamaño, tiempo, negras, blancas, empleado, resultado));
    }


    private void modifyAlias(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final EditText text = new EditText(getContext());

        builder.setTitle(R.string.new_alias).setMessage(R.string.intro_new_alias).setView(text);
        builder.setPositiveButton(R.string.aceptardialog, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface di, int i) {
                ContentValues content = new ContentValues();
                final String auxAlias = text.getText().toString();
                content.put(STRING_ALIAS, auxAlias);
                db.update(STRING_TABLE_NAME,content,"_id==?", new String [] {auxPosition});
                cursor = db.query(STRING_TABLE_NAME, campos, null, null, null, null, null);
                adt.swapCursor(cursor);
                adt.notifyDataSetChanged();
                Toast.makeText(getActivity().getApplicationContext(), "Nuevo alias: " + auxAlias + " modificado.", Toast.LENGTH_LONG).show();

            }
        });
        builder.setNegativeButton(R.string.cancelardialog, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface di, int i) {
            }
        });
        builder.create().show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_listado, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        //datos
        PartidasSQLiteHelper udb = new PartidasSQLiteHelper(getContext(),STRING_DBNAME, null, 1);
        db = udb.getWritableDatabase();
        if (db != null) {
            //long oid = db.insert("Partidas", null, values);
            //System.out.println(oid);

            cursor = db.query(STRING_TABLE_NAME, campos, null, null, null, null, null);



            adt = new SimpleCursorAdapter(getContext(), R.layout.list_query,
                    cursor, new String[]{STRING_ALIAS, STRING_FECHA, STRING_RESULTADO}, new int[]{R.id.val1,R.id.val2, R.id.val3 }, 0);
            lstListado = (ListView) getView().findViewById(R.id.LstListado);
            lstListado.setAdapter(adt);








            lstListado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                    if (listener != null) {
                        cursor.moveToPosition(pos);
                        Score score = new Score(cursor);
                        listener.onScoreSeleccionado(score);
                    }
                }

            });

            lstListado.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {


                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    Cursor cursor = (Cursor) adt.getItem(position);
                    auxPosition = cursor.getString(0);
                    auxFecha = cursor.getString(2);

                    if (mActionMode != null) {
                        return false;
                    }
                    // Start the CAB using the ActionMode.Callback defined above
                    mActionMode = getActivity().startActionMode(mActionModeCallback);
                    view.setSelected(true);
                    return true;
                }

            });

        }
    }


    @Override
    public void onAttach(Context c) {
        super.onAttach(c);
        try {
            listener = (ScoreListener) c;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(c.toString() + " must implement onScoreListener");
        }
    }

    public interface ScoreListener{
        void onScoreSeleccionado(Score score);
    }


    public void setScoreListener(ScoreListener listener) {
        this.listener=listener;
    }


    public SimpleCursorAdapter getAdapterFromFragment(){
        return this.adt;
    }

}
