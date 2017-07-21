package com.example.student.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;

public class LoggedIn extends AppCompatActivity {

    TextView userName, phoneNo;
    ImageButton signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        userName = (TextView) findViewById(R.id.uName);
        phoneNo = (TextView) findViewById(R.id.phoneNo);
        signOut = (ImageButton) findViewById(R.id.logOut);
        final Intent goBack = new Intent(LoggedIn.this, MainActivity.class);

        Intent it = getIntent();
        HashMap<String, String> hashMap = (HashMap<String, String>)it.getSerializableExtra("Val");

        userName.setText(hashMap.get("Name"));
        phoneNo.setText(hashMap.get("Phone No"));

        signOut.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                startActivity(goBack);
            }
        });

    }
}
