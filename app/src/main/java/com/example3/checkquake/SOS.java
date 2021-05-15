package com.example3.checkquake;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SOS extends AppCompatActivity {
    TextView editTextTo, editTextMessage;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sos);

//        editTextMessage = (TextView) findViewById(R.id.textMessage);

        send = (Button) findViewById(R.id.send);

        send.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String to = "jeet11chatterjee@gmail.com,rohitmantri0207@gmail.com";
                String message = "Hi the location of the person is:"+Mylocation.latitude_k+Mylocation.longitude_k;

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                email.putExtra(Intent.EXTRA_TEXT, message);
                email.putExtra(Intent.EXTRA_SUBJECT, "Earthquake SOS! HELP!");

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose a sender client :"));

            }

        });


    }
}



