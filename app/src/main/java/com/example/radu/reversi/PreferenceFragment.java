package com.example.radu.reversi;

import android.app.ListFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.radu.reversi.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreferenceFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String alias = preferences.getString("Alias", "");
        String grid = preferences.getString("Grid", "");
        String timer = preferences.getString("Timer", "");
        String[] values = new String[] { alias, grid, timer};
        /*if(!name.equalsIgnoreCase(""))
        {
            name = name + "  Sethi";  /* Edit the value here*/
        //}
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        Map<String, String> datum = new HashMap<String, String>(2);
        datum.put("title", "Alias");
        datum.put("subtitle", alias);
        data.add(datum);
        datum = new HashMap<String, String>(2);
        datum.put("title", "Grid");
        datum.put("subtitle", grid);
        data.add(datum);
        datum = new HashMap<String, String>(2);
        datum.put("title", "Timer");
        datum.put("subtitle", timer);
        data.add(datum);


        /*for (int i=0; i<titles_str.length; i++) {
            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("title", titles_str[i]);
            datum.put("subtitle", String.valueof(subtitles_str[i]));*/

        //}
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), data,
                android.R.layout.simple_list_item_2,
                new String[] {"title", "subtitle"},
                new int[] {android.R.id.text1,
                        android.R.id.text2});
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Map<String,String> item = (Map<String,String> ) getListAdapter().getItem(position);
        //EditText editText = (EditText) getActivity().findViewById(R.id.textviewardo);
        //editText.setText("subtitle");
        System.out.println(item.get("subtitle"));
        switch (item.get("title")){
            case "Grid":
                break;
            case "Alias":
                System.out.println(item.get("Entro"));
                this.getActivity().openContextMenu(l);
                break;
            case "Timer":
                break;
        }
        /*DetailFrag frag = (DetailFrag) getFragmentManager().findFragmentById(R.id.frag_capt);
        if (frag != null && frag.isInLayout()) {
            Toast.makeText(getActivity(), "Se ejcuto dentro de onListItemClick de ListFrag", Toast.LENGTH_SHORT);
            frag.showText(getCapt(item));
        } else {
            Intent i = new Intent(getActivity(), DetailActivity.class);
            i.putExtra("selected", getCapt(item));
            startActivity(i);
        }*/
    }

    /*private String getCapt(String ship) {
        /*if (ship.toLowerCase().contains("enterprise")) {
            return "Johnathan Archer";
        }
        if (ship.toLowerCase().contains("star trek")) {
            return "James T. Kirk";
        }
        if (ship.toLowerCase().contains("next generation")) {
            return "Jean-Luc Picard";
        }
        if (ship.toLowerCase().contains("deep space 9")) {
            return "Benjamin Sisko";
        }
        if (ship.toLowerCase().contains("voyager")) {
            return "Kathryn Janeway";
        }
        return "???";
    }*/
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo){
        super.onCreateContextMenu(menu, view, contextMenuInfo);
        //MenuInflater menuInflater = getActivity().getMenuInflater();
        //menuInflater.inflate(R.menu.contextal_menu, menu);

        super.onCreateContextMenu(menu, view, contextMenuInfo);
        menu.add(Menu.NONE, R.id.config_items, Menu.NONE, "Menu A");
        menu.add(Menu.NONE, R.id.config_items, Menu.NONE, "Menu B");

        /*Segona Part*/
        /*MenuItem item = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) item.getActionProvider();
        setShareIntent(createShareIntent());*/

    }

    @Override
    public boolean onContextItemSelected(MenuItem item){

        //AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //System.out.println("iNFO POS ES" + position);
        //int pos = info.position;
       /* switch (item.getItemId()){
            case R.id.opcion1:
                Toast.makeText(getApplicationContext(),  "Item del menu: " + item.getTitle() + " seleccionado !!", Toast.LENGTH_LONG).show();
                return true;
            case R.id.opcion2:
                Toast.makeText(getApplicationContext(), "Item del ListView: " + getListAdapter().getItem(position) + " seleccionado !!", Toast.LENGTH_LONG).show();
                return true;
            case R.id.opcion3:
                Toast.makeText(getApplicationContext(), "Item del menu: " + values.get(position)+ " seleccionado se convertira a maysuculas !!", Toast.LENGTH_LONG).show();
                System.out.println(getListAdapter().getItem(position));
                adapter.changeToUpper(position);
                adapter.notifyDataSetChanged();
                return true;
            case R.id.opcion4:
                Toast.makeText(getApplicationContext(), "Item del menu: " + values.get(position)+ " seleccionado se convertira a minusculas!!", Toast.LENGTH_LONG).show();
                adapter.changeToLower(position);
                return true;
            case R.id.opcion5:
                Toast.makeText(getApplicationContext(), "Item del ListView: " + getListAdapter().getItem(position) + " seleccionado se borrara!!", Toast.LENGTH_LONG).show();
                adapter.deleteItem(position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }*/
       return true;
    }



    //@Override
    //protected void onListItemClick(ListView l, View v, int position, long id){
        //this.position = position;
        //String selected_item = getListAdapter().getItem(position).toString();
        //Toast.makeText(this, selected_item + "selected", Toast.LENGTH_SHORT).show();
        //openContextMenu(l);
    //}
}
