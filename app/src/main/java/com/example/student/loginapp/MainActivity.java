package com.example.student.loginapp;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button sigin, signup;
    EditText email, pwd;
    TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sigin = (Button) findViewById(R.id.submit);
        signup = (Button) findViewById(R.id.reg);
        email = (EditText) findViewById(R.id.email);
        pwd = (EditText) findViewById(R.id.pwd);
        res = (TextView) findViewById(R.id.res);

        final DatabaseReference login = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference ip = login.child("UserInfo");

        sigin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                final String ipEmail = email.getText().toString();
                final String ippwd = pwd.getText().toString();

                ip.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot snapshot) {
                        if (snapshot.child(ipEmail).exists()) {
                            DatabaseReference ip2 = login.child("UserInfo").child(ipEmail).child("Password");
                            ip2.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if(snapshot.child(ippwd).exists()){
                                        res.setText(" User Found");
                                        //Get all records
                                        Map<String, String> td = (HashMap<String,String>) dataSnapshot.getValue();
                                        //End of code
                                        Intent loggedIn = new Intent(MainActivity.this, LoggedIn.class);
                                        //loggedIn.putExtra("Val",contactChildren);
                                        startActivity(loggedIn);
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }else{
                            res.setText("Username: Not Found");
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V){
                Intent registerScreen = new Intent(MainActivity.this, Register.class);
                startActivity(registerScreen);
            }
        });

    }
}
