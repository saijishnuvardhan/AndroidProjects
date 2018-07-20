package com.example.praneeth.session_1;

public class Fruit {

    private int imageid;
    private String name;
    public Fruit(String name,int id){
        this.name=name;
        this.imageid=id;
    }

    public int getImageid(){
        return imageid;
    }
    public String getName(){
        return name;
    }
}
