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

public class Phrase extends AppCompatActivity {
    private MediaPlayer media;

    private AudioManager audio;

    private MediaPlayer.OnCompletionListener mCompleteListner=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener=new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                media.pause();
                media.seekTo(0);
            }
            else if(focusChange==AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
            else if(focusChange==AudioManager.AUDIOFOCUS_GAIN){
                media.start();
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        audio=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Work> numbers=new ArrayList<Work>();
        numbers.add(new Work("One","lutti",R.raw.phrase_are_you_coming));
        numbers.add(new Work("Two","ottiko",R.raw.number_two));
        numbers.add(new Work("Three","tolookosu",R.raw.number_three));
        numbers.add(new Work("Four","oyyisa",R.raw.number_four));
        numbers.add(new Work("Five","massokka",R.raw.number_five));
        numbers.add(new Work("Six","temmokka",R.raw.number_six));
        numbers.add(new Work("Seven","kenekaku",R.raw.number_seven));
        numbers.add(new Work("Eight","kawinta",R.raw.number_eight));
        numbers.add(new Work("Nine","wo'e",R.raw.number_nine));
        numbers.add(new Work("Ten","na'aacha",R.raw.number_ten));
        WorkAdapter item=new WorkAdapter(this,numbers,R.color.category_phrases);
        ListView listView=(ListView) findViewById(R.id.list);
        listView.setAdapter(item);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                Work w=numbers.get(position);
                 int result=audio.requestAudioFocus(audioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                releaseMediaPlayer();
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    media = MediaPlayer.create(Phrase.this, w.getAudio());
                    media.start();
                    media.setOnCompletionListener(mCompleteListner);
                }
            }
        });
    }


    public void releaseMediaPlayer(){
        if(media!=null){
            media.release();
            media=null;
            audio.abandonAudioFocus(audioFocusChangeListener);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}


