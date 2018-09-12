package com.example.praneeth.shoping_cart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activty);

        Thread splash=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1800);
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    finish();
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        splash.start();
    }
}
