package com.example.new_cac;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;

public class NoteDetailActivity extends AppCompatActivity {

    private TextView noteContentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        noteContentTextView = findViewById(R.id.noteContentTextView);

        String fileName = getIntent().getStringExtra("fileName");
        if (fileName != null) {
            String content = readNoteFromStorage(fileName);
            noteContentTextView.setText(content);
        } else {
            Toast.makeText(this, "Error loading note", Toast.LENGTH_SHORT).show();
        }
    }

    private String readNoteFromStorage(String fileName) {
        try {
            FileInputStream fis = openFileInput(fileName);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            return new String(buffer);
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to read note";
        }
    }
}
