package com.example.varun.roam;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class DisplayUber extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_uber);
        Log.d("UBER", "I am in the activity!");
        String outputString = getIntent().getStringExtra("productStrings");
        String[] outputs = outputString.split("~");
        ArrayList<Product> products = new ArrayList<Product>();
        ArrayList<String> productStrings = new ArrayList<String>();

        for(int i=1; i< outputs.length; i++){
            String[] elems = outputs[i].split(";");
            Log.d("UBER","outputs[i]: "+outputs[i]);
            Product p = new Product(elems[0],elems[1],elems[2],elems[3]);
            productStrings.add(p.toString());
            products.add(p);
            Log.d("UBER",p.toString());
        }

        ArrayAdapter<String> itemsAdapter;

        UberAdapter m_adapter = new UberAdapter(this, R.layout.uber_list_item, products);

        ListView listView = (ListView) findViewById(R.id.directions_view);
        listView.setAdapter(m_adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_uber, menu);
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
