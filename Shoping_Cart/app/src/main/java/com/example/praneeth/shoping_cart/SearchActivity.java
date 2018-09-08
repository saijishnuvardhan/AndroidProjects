package com.example.praneeth.shoping_cart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final EditText editText=findViewById(R.id.manu);
        final EditText editText1=findViewById(R.id.mo);
        final EditText editText2=findViewById(R.id.min);
        final EditText editText3=findViewById(R.id.max);


        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(editText.getText().toString(),"Model");
                Log.i(editText1.getText().toString(),"Manufacturer");
                Log.i(editText2.getText().toString(),"Min Price");
                Log.i(editText3.getText().toString(),"MaxPrice");
                Intent intent=new Intent(SearchActivity.this,MainActivity.class);
                Bundle bundle=new Bundle();
                if(editText.getText().toString()==""){
                    bundle.putString("Manufacturer",null);
                }
                bundle.putString("Manufacturer",editText.getText().toString());
                if (editText1.getText().toString() == "") {
                    bundle.putString("Model",null);
                }
                bundle.putString("Model",editText1.getText().toString() );
                if(editText2.getText().toString()==""){
                    bundle.putString("min_price",null );
                }
                bundle.putString("min_price",editText2.getText().toString());
                if(editText3.getText().toString() == ""){
                    bundle.putString("max_price",null);
                }
                bundle.putString("max_price",editText3.getText().toString());
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }
}
