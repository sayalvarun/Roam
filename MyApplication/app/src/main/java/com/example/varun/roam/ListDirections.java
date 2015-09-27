package com.example.varun.roam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ListDirections extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_directions);
        String totalDuration = getIntent().getExtras().getString("totalDuration");
        String totalDistance = getIntent().getExtras().getString("totalDistance");
        ArrayList<String> elems = getIntent().getExtras().getStringArrayList("directions");
        ArrayList<String> toStrings = new ArrayList<String>();
        Direction[] directions = new Direction[elems.size()-1];
        for(int i=0; i<directions.length; i++){
            String[] subElems = elems.get(i).split(";");
            directions[i] = new Direction(subElems[0],subElems[1],subElems[2]);
            toStrings.add(directions[i].toString());
            Log.d("ELEM:",directions[i].toString());
        }

        ArrayAdapter<String> itemsAdapter;
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, toStrings);

        ListView listView = (ListView) findViewById(R.id.directions_view);
        listView.setAdapter(itemsAdapter);

        // /Log.d("Dur: ", totalDuration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_directions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
