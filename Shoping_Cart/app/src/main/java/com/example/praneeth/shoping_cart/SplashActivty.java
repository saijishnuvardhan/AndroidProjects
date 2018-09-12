package com.example.praneeth.shoping_cart;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activty);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();


        Thread splash=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);
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
