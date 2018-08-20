package com.example.android.quakereport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Quake {

    private String magnitude;
    private String city;
    private String dat;

    public Quake(String m,String c,String d){
        magnitude=m;
        city=c;
        dat=d;
    }

    public String getMagnitude(){
        return magnitude;
    }

    public String getCity(){
        return city;
    }

    public String getDate(){
        return dat;
    }
}
