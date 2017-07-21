package com.example.student.loginapp;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Akshay";
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
                res.setText("Hello");
                final String ipEmail = email.getText().toString();
                final String ippwd = pwd.getText().toString();

                ip.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot snapshot) {
                        if (ipEmail.length() > 0 && snapshot.child(ipEmail).exists()) {
                            res.setText("User Name Found");
                            DatabaseReference ip2 = login.child("UserInfo").child(ipEmail);
                            ip2.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    HashMap<String, String> userInfoFB = new HashMap<String, String>();
                                    ArrayList<String> ids = new ArrayList<String>();
                                    for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                                        ids.add(childSnapshot.getValue().toString());
                                    }
                                    String resUp = ids.get(0).toString();
                                    resUp = resUp.substring(1,resUp.length()-1);
                                    String[] resVal = resUp.split(", ");
                                    for (int i =0; i<resVal.length; i++) {
                                        String[] innVal = resVal[i].split("=");
                                        userInfoFB.put(innVal[0], innVal[1]);
                                    }
                                    Log.i(TAG, "UserInfoFB " + userInfoFB);
                                    if(ippwd.length() > 0 && userInfoFB.get("Password").equals(ippwd)){
                                        res.setText("User Found");
                                        Intent loggedIn = new Intent(MainActivity.this, LoggedIn.class);
                                        loggedIn.putExtra("Val", userInfoFB);
                                        startActivity(loggedIn);
                                    }else{
                                        res.setText("Password "+ippwd+" not found");
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }else{
                            res.setText("Username "+ipEmail+" Not Found");
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
