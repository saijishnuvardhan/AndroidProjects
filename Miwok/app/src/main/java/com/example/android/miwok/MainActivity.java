/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String PRA = "com.example.android.miwok";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);
        TextView t=findViewById(R.id.numbers);
        t.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Opening Numbers List",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, Numbers.class);
                startActivity(intent);
            }
        });
        TextView family=findViewById(R.id.family);
        family.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Opening Family List",Toast.LENGTH_LONG).show();
                Intent family=new Intent(MainActivity.this,Family.class);
                startActivity(family);
            }
        });
        TextView color=findViewById(R.id.colors);
        color.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Opening Colors List",Toast.LENGTH_LONG).show();
                Intent color=new Intent(MainActivity.this,Colours.class);
                startActivity(color);
            }
        });
        TextView Phrases=findViewById(R.id.phrases);
        Phrases.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Opening Phrases List",Toast.LENGTH_LONG).show();
                Intent phrase=new Intent(MainActivity.this,Phrase.class);
                startActivity(phrase);
            }
        });


    }

}
