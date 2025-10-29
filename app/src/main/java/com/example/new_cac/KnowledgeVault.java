package com.example.new_cac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;

public class KnowledgeVault extends BaseActivity {

    private EditText noteTitle, inputBox;
    private Button saveBtn, showNotesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityLayout(R.layout.knowledge_vault);

        // Initialize views
        noteTitle = findViewById(R.id.noteTitle);
        inputBox = findViewById(R.id.inputBox);
        saveBtn = findViewById(R.id.saveNote);
        showNotesBtn = findViewById(R.id.showNotesBtn);

        // Save button functionality
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleText = noteTitle.getText().toString().trim();
                String noteText = inputBox.getText().toString().trim();

                if (titleText.isEmpty()) {
                    Toast.makeText(KnowledgeVault.this, "Please enter a title", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (noteText.isEmpty()) {
                    Toast.makeText(KnowledgeVault.this, "Please enter a note", Toast.LENGTH_SHORT).show();
                    return;
                }

                saveNoteToStorage(titleText, noteText);
            }
        });

        // Show Notes button functionality – open full NotesListActivity page
        showNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KnowledgeVault.this, NotesListActivity.class);
                startActivity(intent);
                finish(); // closes KnowledgeVault so NotesListActivity fully replaces it
            }
        });
    }

    // Save note to storage with custom title
    private void saveNoteToStorage(String title, String noteText) {
        try {
            // Clean title for filename
            String safeTitle = title.replaceAll("[^a-zA-Z0-9_\\-]", "_");
            String fileName = "note_" + safeTitle + ".txt";

            FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);
            fos.write(noteText.getBytes());
            fos.close();

            Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show();
            inputBox.setText(""); // clear note content
            noteTitle.setText(""); // clear title
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save note", Toast.LENGTH_SHORT).show();
        }
    }

    // Optional: delete a note file (can be used in NotesListActivity)
    public void deleteNote(String fileName) {
        File file = new File(getFilesDir(), fileName);
        if (file.exists()) {
            boolean deleted = file.delete();
            Toast.makeText(this, deleted ? "Note deleted" : "Failed to delete note", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note not found", Toast.LENGTH_SHORT).show();
        }
    }
}
