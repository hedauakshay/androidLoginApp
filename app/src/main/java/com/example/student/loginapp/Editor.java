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

import java.util.HashMap;

public class Editor extends AppCompatActivity {

    ImageButton saveCont, goBack;
    EditText note;

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

        final DatabaseReference update = FirebaseDatabase.getInstance().getReference();
        Log.i("NoteContent", noteContent);
        note.setText(noteContent);

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
                String key =  update.child("UserInfo").child("hedauakshay").push().getKey();
                update.child("UserInfo").child("hedauakshay").child("-KpNOC90YQtYQ2uBluqz").child("NotePad").runTransaction(new Transaction.Handler() {
                    @Override
                    public Transaction.Result doTransaction(MutableData mutableData) {
                        String s = mutableData.getValue(String.class);
                        mutableData.setValue(note.getText().toString());
                        return Transaction.success(mutableData);
                    }

                    @Override
                    public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {

                    }
                });
                Toast.makeText(Editor.this, "Save"+key, Toast.LENGTH_LONG).show();
            }
        });
    }
}
