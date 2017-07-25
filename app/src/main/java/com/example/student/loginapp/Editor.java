package com.example.student.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Editor extends AppCompatActivity {

    ImageButton saveCont, goBack;
    EditText note;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Intent it = getIntent();
        final String noteContent = (String) it.getSerializableExtra("note");
        saveCont = (ImageButton) findViewById(R.id.save);
        note = (EditText) findViewById(R.id.notepad);
        goBack = (ImageButton) findViewById(R.id.back);
        final Intent goBackTab = new Intent(Editor.this, LoggedIn.class);
        final HashMap<String, String> hashMap = (HashMap<String, String>)it.getSerializableExtra("Val");
        final String ipEmail = (String)it.getSerializableExtra("emailID");
        final String uName = (String)it.getSerializableExtra("uName");

        final DatabaseReference update = FirebaseDatabase.getInstance().getReference();
        Log.i("NoteContent", noteContent);
        note.setText(noteContent);
        //String key = null;

        update.child("UserInfo").child(uName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                try {
                    if (snapshot.getValue() != null) {
                        try {
                             // your name values you will get here
                            String response = snapshot.getValue().toString();
                            int id = response.indexOf("=");
                            key = response.substring(1,id);
                            Log.i("NoteContent2", key);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.i("TAG", " it's null.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        goBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                Log.i("NoteContent", "Clicked");
                goBackTab.putExtra("Val", hashMap);
                startActivity(goBackTab);
            }
        });

        saveCont.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                Log.i("ipEmail", ipEmail);
                //String key =  update.child("UserInfo").child("hedauakshay").push().getKey();
                changeRecord(note.getText().toString(), uName);

                Toast.makeText(Editor.this, "Save", Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void changeRecord(final String message, String uName){
        final DatabaseReference updateRec = FirebaseDatabase.getInstance().getReference();
        updateRec.child("UserInfo").child(uName).child(key).child("NotePad").runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                //String s = mutableData.getValue(String.class);
                mutableData.setValue(message);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {

            }
        });
    }
}
