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

public class Family extends AppCompatActivity {
     private MediaPlayer mediaPlayer;

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

            if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK||focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT){
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
        setContentView(R.layout.activity_family);
        audio=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Work> numbers=new ArrayList<Work>();
        numbers.add(new Work("father","Nanna",R.drawable.family_father,R.raw.family_father));
        numbers.add(new Work("Mummy","Amma",R.drawable.family_mother,R.raw.family_mother));
        numbers.add(new Work("Maternal Grand Mother","Ammama",R.drawable.family_grandmother,R.raw.family_grandmother));
        numbers.add(new Work("Grand Father","Tatayya",R.drawable.family_grandfather,R.raw.family_grandfather));
        numbers.add(new Work("Paternal Grand Mother","nannama",R.drawable.family_grandmother,R.raw.family_grandmother));
        numbers.add(new Work("Older Sister","akka",R.drawable.family_older_sister,R.raw.family_older_sister));
        numbers.add(new Work("Older Brother","annayya",R.drawable.family_older_brother,R.raw.family_older_brother));
        numbers.add(new Work("Younger Sister","Chelli",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        numbers.add(new Work("Younger Brother","tammudu",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        numbers.add(new Work("Daughter","kuthuru",R.drawable.family_daughter,R.raw.family_daughter));
        numbers.add(new Work("Son","koduku",R.drawable.family_son,R.raw.family_son));
        WorkAdapter item=new WorkAdapter(this,numbers,R.color.category_family);
        ListView listView=(ListView) findViewById(R.id.family);
        listView.setAdapter(item);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                Work w=numbers.get(position);
                 int result=audio.requestAudioFocus(audioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                releaseMediaPlayer();
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(Family.this, w.getAudio());
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
            audio.abandonAudioFocus(audioFocusChangeListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}

