package com.example3.checkquake;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example3.checkquake.ACTIVITIES.MapsActivity;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class SOS extends AppCompatActivity {
    TextView editTextTo, editTextMessage;
    Button send;
    FirebaseFirestore fstore;
    FirebaseAuth fAuth;
    String userID;
    String email1;
    String email2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sos);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fstore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot value, FirebaseFirestoreException error) {
                email1 = value.getString("Num1");
                email2 = value.getString("Num2");
            }
        });

//        editTextMessage = (TextView) findViewById(R.id.textMessage);

        send = (Button) findViewById(R.id.send);

        send.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {


                String to = email1+","+email2;
                String message = "Hi the location of the person is:\n"+Mylocation.latitude_k+" N\n"+Mylocation.longitude_k+" E" ;

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



