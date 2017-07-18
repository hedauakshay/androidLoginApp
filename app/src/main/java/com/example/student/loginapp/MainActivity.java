package com.example.student.loginapp;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        res = (TextView) findViewById(R.id.reg);

        DatabaseReference login = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference ip = login.child("UserCredentials");

        sigin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                String ipEmail = email.getText().toString();
                String ippwd = pwd.getText().toString();
                res.setText(ipEmail+" : "+ippwd);
                DatabaseReference childEmail = ip.child("Email");
                childEmail.setValue(ipEmail);
                DatabaseReference childPwd = ip.child("Password");
                childPwd.setValue(ippwd);
            }
        });

        res.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V){
                Intent registerScreen = new Intent(MainActivity.this, Register.class);
                startActivity(registerScreen);
            }
        });

    }
}
