package com.example3.checkquake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search =(ImageButton)findViewById(R.id.bus1);
        search.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {

                Intent intent = new Intent(MainActivity.this,
                        MapsActivity.class);
                startActivity(intent);


            }
        });




    }
}