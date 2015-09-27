package com.example.varun.roam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by varun on 9/27/15.
*/
public class UberAdapter extends ArrayAdapter<Product> {
    private ArrayList<Product> objects;

    public UberAdapter(Context context, int textViewResourceId, ArrayList<Product> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        // assign the view we are converting to a local variable
        View v = convertView;

        // first check to see if the view is null. if so, we have to inflate it.
        // to inflate it basically means to render, or show, the view.
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item, null);
        }

		/*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 *
		 * Therefore, i refers to the current Item object.
		 */
        Product i = objects.get(position);

        if (i != null) {

            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.

            TextView capacity = (TextView) v.findViewById(R.id.capacity);
            TextView base = (TextView) v.findViewById(R.id.base);
            TextView cpm = (TextView) v.findViewById(R.id.cpm);
            TextView min = (TextView) v.findViewById(R.id.minimum);

            // check to see if each individual textview is null.
            // if not, assign some text!
            if (capacity != null){
                capacity.setText(i.capacity);
            }

            if (base != null){
                base.setText(i.base);
            }
            if (cpm != null){
                cpm.setText(i.cost_per_mile);
            }
            if(min != null){
                min.setText(i.minimum);
            }

        }

        // the view must be returned to our activity
        return v;
    }
}
