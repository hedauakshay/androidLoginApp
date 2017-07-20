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

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    Button signup;
    EditText fname, email, pwd, phoneNo, uName;
    TextView res;
    Spinner gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signup = (Button) findViewById(R.id.register);
        fname = (EditText) findViewById(R.id.name);
        uName = (EditText) findViewById(R.id.userName);
        email = (EditText) findViewById(R.id.email);
        pwd = (EditText) findViewById(R.id.pwd);
        phoneNo = (EditText) findViewById(R.id.phone);
        gender = (Spinner) findViewById(R.id.gender);
        res = (TextView) findViewById(R.id.textView);

        signup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                String ipfullName = fname.getText().toString();
                String ipemailID =  email.getText().toString();
                String ippwd = pwd.getText().toString();
                String ipPhone = phoneNo.getText().toString();
                String ipSex = gender.getSelectedItem().toString();
                String uname = uName.getText().toString();

                Map<String, String> entry = new HashMap<>();
                entry.put("EmailID",ipemailID);
                entry.put("Name", ipfullName);
                entry.put("Password", ippwd);
                entry.put("Phone No", ipPhone);
                entry.put("Sex", ipSex);

                DatabaseReference loginDB = FirebaseDatabase.getInstance().getReference();
                DatabaseReference ll = loginDB.child("UserInfo");
                DatabaseReference newPostRef = ll.child(uname).push();
                newPostRef.setValue(entry);

                Intent login = new Intent(Register.this, MainActivity.class);
                startActivity(login);
            }
        });

    }
}
