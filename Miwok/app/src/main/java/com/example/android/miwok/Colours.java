package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Colours extends AppCompatActivity {
    private MediaPlayer mediaPlayer;

    private AudioManager audio;
    private MediaPlayer.OnCompletionListener mCompleteListner=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener=new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
         if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
             mediaPlayer.pause();
             mediaPlayer.seekTo(0);
         }
         else if(focusChange==AudioManager.AUDIOFOCUS_LOSS){
             releaseMediaPlayer();
         }
         else if(focusChange==AudioManager.AUDIOFOCUS_GAIN){
             mediaPlayer.start();
         }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colours);

        audio = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Work> numbers=new ArrayList<Work>();
        numbers.add(new Work("Red","yerupu",R.drawable.color_red,R.raw.color_red));
        numbers.add(new Work("Yellow","pasupu",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        numbers.add(new Work("Dusty yellow","gandham",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        numbers.add(new Work("Green","Aakupaccha",R.drawable.color_green,R.raw.color_green));
        numbers.add(new Work("Black","Nalupu",R.drawable.color_black,R.raw.color_black));
        numbers.add(new Work("White","telupu",R.drawable.color_white,R.raw.color_white));
        numbers.add(new Work("Gray","Budidha",R.drawable.color_gray,R.raw.color_gray));
        numbers.add(new Work("Brown","Braun raṅgu",R.drawable.color_brown,R.raw.color_brown));
        WorkAdapter item=new WorkAdapter(this,numbers,R.color.category_colors);
        ListView listView=(ListView) findViewById(R.id.colors);
        listView.setAdapter(item);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                Work w=numbers.get(position);
                int result=audio.requestAudioFocus(onAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                releaseMediaPlayer();
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(Colours.this, w.getAudio());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(mCompleteListner);
                }
            }
        });
    }

    public void releaseMediaPlayer(){
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer=null;
            audio.abandonAudioFocus(onAudioFocusChangeListener);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

}


