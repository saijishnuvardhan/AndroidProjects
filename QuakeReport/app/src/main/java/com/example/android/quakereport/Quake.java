package com.example.android.quakereport;

public class Quake {

    double magnitude;
    private String direction;
    private String city;
    private String dat;
    private String time;

    public Quake(double m,String dir,String c,String d,String t){
        magnitude=m;
        city=c;
        dat=d;
        direction=dir;
        time=t;
    }

    public String getDirection() {
        return direction;
    }

    public double getMagnitude(){
        return magnitude;
    }

    public String getCity(){
        return city;
    }

    public String getDate(){
        return dat;
    }

    public String getTime() {
        return time;
    }
}
