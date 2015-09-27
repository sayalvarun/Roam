package com.example.varun.roam;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        myToolbar.setLogo(R.drawable.icon_36);

        String totalDuration = getIntent().getExtras().getString("totalDuration");
        String totalDistance = getIntent().getExtras().getString("totalDistance");
        ArrayList<String> elems = getIntent().getExtras().getStringArrayList("directions");
        ArrayList<String> toStrings = new ArrayList<String>();

        ArrayList<Direction> directions = new ArrayList<Direction>();
        for(int i=0; i<elems.size(); i++){
            String[] subElems = elems.get(i).split(";");
            directions.add(new Direction(subElems[0],subElems[1],subElems[2]));
            toStrings.add(directions.get(i).toString());
            Log.d("ELEM:",directions.get(i).toString());
        }
        
        DirectionAdapter m_adapter = new DirectionAdapter(this, R.layout.list_item, directions);

        ListView listView = (ListView) findViewById(R.id.directions_view);
        listView.setAdapter(m_adapter);

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
