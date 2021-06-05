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

    public SharedPreferences sp;



    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    public FirebaseAuth mAuth;
    public FirebaseAuth.AuthStateListener mAuthListner;
    private static final String TAG = "login";
    private EditText email;
    private EditText password;
    private Button login;
    private SharedPreferences preferences;
    public static final String PREFS_NAME = "LoginPrefs";
    public static String username;
    private Button sign;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        sp = getSharedPreferences("login",MODE_PRIVATE);

//        if(sp.getBoolean("logged",false)){
//            goToMainActivity();
//        }
//        else{
//            Intent i = new Intent(login.this,login.class);
//            startActivity(i);
//            finish();
//        }


        sign = (Button) findViewById(R.id.signup);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.signupdata);

        sign.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                Intent intent = new Intent(login.this, signup.class);
                startActivity(intent);
            }
        });

//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                      }
//            }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailTxt = email.getText().toString();
                String passTxt = password.getText().toString();

                if (emailTxt.isEmpty() && passTxt.isEmpty()) {
                        Toast.makeText(login.this, "Enter all the details", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(login.this, login.class));
                    finish();
                    }

               else {
                    Toast.makeText(login.this, "One moment trying to log user in!", Toast.LENGTH_LONG).show();
                    mAuth.signInWithEmailAndPassword(emailTxt, passTxt).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(login.this, "Wrong Credentials", Toast.LENGTH_LONG).show();
                            } else {
                                sp.edit().putBoolean("logged",true).apply();
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
                username = user.getEmail();
                System.out.println(username);

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

//    @Override
//    protected void onStop(){
//        super.onStop();
////        mAuth.getInstance().signOut();
//            mAuth.removeAuthStateListener(mAuthListner);
//
//    }
    @Override
    public void onBackPressed() {
// empty so nothing happens
    }
    public void goToMainActivity(){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}