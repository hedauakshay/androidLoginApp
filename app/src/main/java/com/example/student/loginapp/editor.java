package com.example.student.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class editor extends AppCompatActivity {

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
        final Intent goBackTab = new Intent(editor.this, LoggedIn.class);

        Log.i("NoteContent", noteContent);
        note.setText(noteContent);

        goBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                Log.i("NoteContent", "Clicked");
            }
        });
    }
}
