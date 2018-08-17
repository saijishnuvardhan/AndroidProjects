package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Numbers extends AppCompatActivity {

    private MediaPlayer m;

    private AudioManager audioManager;

    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener=new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
          if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
              m.pause();
              m.seekTo(0);
          }
          else if(focusChange==AudioManager.AUDIOFOCUS_LOSS){

              releaseMediaPlayer();
          }
          else if(focusChange==AudioManager.AUDIOFOCUS_GAIN){
              m.start();
          }
        }
    };

    private MediaPlayer.OnCompletionListener mCompleteListner=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
           releaseMediaPlayer();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        audioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Work> numbers=new ArrayList<Work>();
        numbers.add(new Work("One","lutti",R.drawable.number_one,R.raw.number_one));
        numbers.add(new Work("Two","ottiko",R.drawable.number_two,R.raw.number_two));
        numbers.add(new Work("Three","tolookosu",R.drawable.number_three,R.raw.number_three));
        numbers.add(new Work("Four","oyyisa",R.drawable.number_four,R.raw.number_four));
        numbers.add(new Work("Five","massokka",R.drawable.number_five,R.raw.number_five));
        numbers.add(new Work("Six","temmokka",R.drawable.number_six,R.raw.number_six));
        numbers.add(new Work("Seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        numbers.add(new Work("Eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
        numbers.add(new Work("Nine","wo'e",R.drawable.number_nine,R.raw.number_nine));
        numbers.add(new Work("Ten","na'aacha",R.drawable.number_ten,R.raw.number_ten));
        WorkAdapter item=new WorkAdapter(this,numbers,R.color.category_numbers);
        ListView listView=(ListView) findViewById(R.id.list);
        listView.setAdapter(item);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                Work w=numbers.get(position);
                 int result=audioManager.requestAudioFocus(audioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                releaseMediaPlayer();
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                {
                    m=MediaPlayer.create(Numbers.this,w.getAudio());
                    m.start();
                    m.setOnCompletionListener(mCompleteListner);
                }

            }
        });
        }

        public void releaseMediaPlayer(){
           if(m!=null){
               m.release();
               m=null;
               audioManager.abandonAudioFocus(audioFocusChangeListener);
           }
        }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();

    }

    }

