package com.example.student.loginapp;

import android.content.Intent;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class Register extends AppCompatActivity {

    Button signup;
    EditText fname, email, pwd, phoneNo;
    TextView res;
    Spinner gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signup = (Button) findViewById(R.id.register);
        fname = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        pwd = (EditText) findViewById(R.id.pwd);
        phoneNo = (EditText) findViewById(R.id.phone);
        gender = (Spinner) findViewById(R.id.gender);
        res = (TextView) findViewById(R.id.textView);

        DatabaseReference login = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference ip = login.child("UserInfo");

        signup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                String ipfullName = fname.getText().toString();
                String ipemailID =  email.getText().toString();
                String ippwd = pwd.getText().toString();
                String ipPhone = phoneNo.getText().toString();
                String ipSex = gender.getSelectedItem().toString();
                res.setText(ipSex);
                Intent login = new Intent(Register.this, MainActivity.class);
                startActivity(login);
            }
        });

    }
}
