package com.example3.checkquake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example3.checkquake.ACTIVITIES.MapsActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {
    private Button sign;
    private EditText name;
    private EditText email;
    private EditText password;
    private EditText number;
    private EditText number2;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;
    private FirebaseFirestore fstore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        sign = (Button) findViewById(R.id.signupdata);
        name = (EditText) findViewById(R.id.PersonName);
        email = (EditText) findViewById(R.id.EmailAddress);
        password = (EditText) findViewById(R.id.Password);
        number = (EditText) findViewById(R.id.Number);
        number2 = (EditText) findViewById(R.id.Number2);

        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

//        if (mAuth.getCurrentUser() != null) {
//            startActivity(new Intent(getApplicationContext(), MainActivity
//                    .class));
//            finish();
//        }


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String emailTxt = email.getText().toString();
                String passTxt = password.getText().toString();
                String nameTxt = name.getText().toString();
                String numTxt = number.getText().toString();
                String num2Txt = number2.getText().toString();

                if(emailTxt.isEmpty()&&passTxt.isEmpty())

                {
                    Toast.makeText(signup.this, "Enter all the details", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(signup.this, signup.class));
                    finish();
                }

                else {
                    Toast.makeText(signup.this, "One Moment trying to signup user!", Toast.LENGTH_LONG).show();

                    mAuth.createUserWithEmailAndPassword(emailTxt, passTxt).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(signup.this, "Success!", Toast.LENGTH_LONG).show();
//                                now write to database
                                userID = mAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fstore.collection("users").document(userID);
                                Map<String, Object> user = new HashMap<>();
                                user.put("Name", nameTxt);
                                user.put("Email", emailTxt);
                                user.put("Num1", numTxt);
                                user.put("Num2", num2Txt);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
//                                    Log.d(TAG, "onSuccess: user profile created for " + userID);
                                    }
                                });
                                startActivity(new Intent(signup.this, MainActivity.class));
                            } else {
                                Toast.makeText(signup.this, "Error!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }
        });
    }
}