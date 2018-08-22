package com.example.android.quakereport;

public class Quake {

    double magnitude;
    private String direction;
    private String city;
    private String dat;
    private String time;
    private String url;

    public Quake(double m,String dir,String c,String d,String t,String u){
        magnitude=m;
        city=c;
        dat=d;
        direction=dir;
        time=t;
        url=u;
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

    public String getUrl(){ return url;}
}
