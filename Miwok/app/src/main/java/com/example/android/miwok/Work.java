package com.example.android.miwok;

public class Work {

    private String dTranslation,miwokTranslation;
    private int resourceID=NOIMAGEPROVIDED;
    private int mAudioId;

    public static final int NOIMAGEPROVIDED=-1;

    public Work(String dtal, String miwokTranslation,int Audio){
        this.dTranslation=dtal;
        this.miwokTranslation=miwokTranslation;
        this.mAudioId=Audio;

    }

    public Work(String dtal,String miwokTranslation,int resourceID,int Audio){
        this.dTranslation=dtal;
        this.miwokTranslation=miwokTranslation;
        this.resourceID=resourceID;
        this.mAudioId=Audio;
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
    public int getAudio(){return mAudioId;}
}

