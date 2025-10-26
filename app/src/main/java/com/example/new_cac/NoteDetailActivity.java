package com.example.new_cac;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class NoteDetailActivity extends BaseActivity {

    private EditText noteContentEditText;
    private Button saveBtn, cancelBtn;
    private String fileName; // Name of the current note file

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityLayout(R.layout.activity_note_detail);

        noteContentEditText = findViewById(R.id.noteContentEditText);
        saveBtn = findViewById(R.id.saveBtn);
        cancelBtn = findViewById(R.id.cancelBtn);

        // Get the filename from intent
        fileName = getIntent().getStringExtra("fileName");
        if (fileName != null) {
            loadNoteContent(fileName);
        }

        // Save button
        saveBtn.setOnClickListener(view -> saveNoteContent(fileName));

        // Cancel button
        cancelBtn.setOnClickListener(view -> finish()); // just go back without saving
    }

    // Load the note content into the EditText
    private void loadNoteContent(String fileName) {
        try {
            FileInputStream fis = openFileInput(fileName);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            String content = new String(buffer);
            noteContentEditText.setText(content);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error loading note", Toast.LENGTH_SHORT).show();
        }
    }

    // Save the edited note content
    private void saveNoteContent(String fileName) {
        String content = noteContentEditText.getText().toString();
        try {
            FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
            finish(); // go back to notes list
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error saving note", Toast.LENGTH_SHORT).show();
        }
    }
}
