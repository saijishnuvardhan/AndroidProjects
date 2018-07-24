package com.example.android.miwok;

public class Work {

    private String dTranslation,miwokTranslation;
    private int resourceID=NOIMAGEPROVIDED;

    public static final int NOIMAGEPROVIDED=-1;

    public Work(String dtal, String miwokTranslation){
        this.dTranslation=dtal;
        this.miwokTranslation=miwokTranslation;
    }

    public Work(String dtal,String miwokTranslation,int resourceID){
        this.dTranslation=dtal;
        this.miwokTranslation=miwokTranslation;
        this.resourceID=resourceID;
    }

    public String getdTranslation() {
        return dTranslation;
    }

    public String getMiwokTranslation() {
        return miwokTranslation;
    }

    public int getResourceID(){
        return resourceID;
    }

    public boolean hasImage(){
        return resourceID!=NOIMAGEPROVIDED;
    }
}

