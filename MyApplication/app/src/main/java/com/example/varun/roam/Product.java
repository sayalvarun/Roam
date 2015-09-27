package com.example.varun.roam;

/**
 * Created by varun on 9/27/15.
 */
public class Product {

    public String capacity;
    public String base;
    public String cost_per_mile;
    public String minimum;

    public Product(String cap, String b, String cpm, String minimum){
        capacity = cap;
        base = b;
        cost_per_mile = cpm;
        this.minimum = minimum;
    }

    @Override
    public String toString() {
        return "Capacity: "+capacity+"\t Base: "+base+"\n Cost Per Mile: "+cost_per_mile+"\t Minimum: "+minimum;
    }
}
