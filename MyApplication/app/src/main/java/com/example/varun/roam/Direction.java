package com.example.varun.roam;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by varun on 9/27/15.
 */
public class Direction implements Parcelable{
    private String distance;
    private String duration;
    private String directionText;

    public Direction(String dist, String dur, String directionText){
        distance = dist;
        duration = dur;
        this.directionText = directionText;
    }

    public String getDistance(){
        return distance;
    }

    public String getDuration(){
        return duration;
    }

    public String getDirectionText(){
        return directionText;
    }

    @Override
    public String toString() {
        return distance+"\t"+duration+"\n"+directionText+"\n";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(distance);
        parcel.writeString(duration);
        parcel.writeString(directionText);
        // Again continue doing this for the rest of your member data
    }
}
