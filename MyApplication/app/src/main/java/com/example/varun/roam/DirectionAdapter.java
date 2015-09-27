package com.example.varun.roam;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


/**
 * Created by phil on 9/27/15.
 */
public class DirectionAdapter extends ArrayAdapter<Direction>{

    private ArrayList<Direction> objects;

    public DirectionAdapter(Context context, int textViewResourceId, ArrayList<Direction> objects) {
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
        Direction i = objects.get(position);

        if (i != null) {

            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.

            TextView direc = (TextView) v.findViewById(R.id.toptext);
            TextView dur = (TextView) v.findViewById(R.id.middletext);
            TextView instr = (TextView) v.findViewById(R.id.middletextdata);

            // check to see if each individual textview is null.
            // if not, assign some text!
            if (direc != null){
                direc.setText(i.getDistance());
            }

            if (dur != null){
                dur.setText(i.getDuration());
            }
            if (instr != null){
                instr.setText(i.getDirectionText());
            }
            
        }

        // the view must be returned to our activity
        return v;

    }

}
