package com.example.student.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;

public class LoggedIn extends AppCompatActivity {

    TextView userName, phoneNo;
    ImageButton signOut, editorTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        userName = (TextView) findViewById(R.id.uName);
        phoneNo = (TextView) findViewById(R.id.phoneNo);
        signOut = (ImageButton) findViewById(R.id.logOut);
        editorTab = (ImageButton) findViewById(R.id.editor);
        final Intent goBack = new Intent(LoggedIn.this, MainActivity.class);
        final Intent editorActivity = new Intent(LoggedIn.this, Editor.class);

        Intent it = getIntent();
        final HashMap<String, String> hashMap = (HashMap<String, String>)it.getSerializableExtra("Val");
        final String uName = (String)it.getSerializableExtra("ipEmail");

        userName.setText(hashMap.get("Name"));
        phoneNo.setText(hashMap.get("Phone No"));

        signOut.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                startActivity(goBack);
            }
        });

        editorTab.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                if (hashMap.containsKey("NotePad")) {
                    editorActivity.putExtra("note", hashMap.get("NotePad"));
                }else{
                    editorActivity.putExtra("note", "");
                }
                editorActivity.putExtra("Val", hashMap);
                editorActivity.putExtra("emailID", hashMap.get("EmailID"));
                editorActivity.putExtra("uName", uName);
                startActivity(editorActivity);
            }
        });

    }
}
