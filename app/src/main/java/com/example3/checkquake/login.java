package com.example3.checkquake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;
    private static final String TAG = "login";
    private EditText email;
    private EditText password;
    private Button login;
    private SharedPreferences preferences;
    public static final String PREFS_NAME = "LoginPrefs";
    public static String username;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);



        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailTxt = email.getText().toString();
                String passTxt = password.getText().toString();


                if (emailTxt != "" && passTxt != "") {
                    mAuth.signInWithEmailAndPassword(emailTxt, passTxt).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(login.this, "Wrong Credentials", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(login.this, "Success!", Toast.LENGTH_LONG).show();
//                                now write to database
                                startActivity(new Intent(login.this, MainActivity.class));
                                finish();
                            }
                        }
                    });
                }
            }
        });

        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("message1");

        databaseReference.setValue("Hello Akshat!");

        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                username = "abhishek@gmail.com";

                if (user != null) {
//                    user is signed into Checkquake
                    Log.d(TAG, "user signed in");
                } else
//                    user signed out
                {
                    Log.d(TAG, "user signed out");
                }

            }
        };
    }



    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }

    @Override
    protected void onStop(){
        super.onStop();

        if(mAuthListner!=null){
            mAuth.removeAuthStateListener(mAuthListner);
        }
    }
}