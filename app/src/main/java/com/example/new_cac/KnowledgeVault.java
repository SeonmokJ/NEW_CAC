package com.example.new_cac; // replace with your package name

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.util.Date;

public class KnowledgeVault extends AppCompatActivity {

    private EditText inputBox;
    private Button saveBtn, toggleListBtn;
    private ListView notesListView;
    private ArrayList<String> noteFiles;
    private ArrayAdapter<String> adapter;
    private boolean isListVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_vault);

        // Initialize views
        inputBox = findViewById(R.id.inputBox);
        saveBtn = findViewById(R.id.saveNote);
        toggleListBtn = findViewById(R.id.showNotesBtn);

        // Dynamically add a ListView below toggle button
        notesListView = new ListView(this);
        notesListView.setVisibility(View.GONE); // initially hidden
        ((androidx.constraintlayout.widget.ConstraintLayout) findViewById(R.id.main))
                .addView(notesListView);

        noteFiles = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, noteFiles);
        notesListView.setAdapter(adapter);

        // Save button functionality
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noteText = inputBox.getText().toString().trim();
                if (!noteText.isEmpty()) {
                    saveNoteToStorage(noteText);
                } else {
                    Toast.makeText(KnowledgeVault.this, "Please enter a note first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Toggle list functionality
        toggleListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isListVisible) {
                    notesListView.setVisibility(View.GONE);
                    isListVisible = false;
                } else {
                    loadNotes();
                    notesListView.setVisibility(View.VISIBLE);
                    isListVisible = true;
                }
            }
        });

        // Click on a note to view its content
        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String fileName = noteFiles.get(position);
                String content = readNoteFromStorage(fileName);
                Toast.makeText(KnowledgeVault.this, content, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveNoteToStorage(String noteText) {
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
            String fileName = "note_" + timeStamp + ".txt";

            FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);
            fos.write(noteText.getBytes());
            fos.close();

            Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show();
            inputBox.setText(""); // clear input
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save note", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadNotes() {
        noteFiles.clear();
        String[] files = fileList();
        for (String file : files) {
            if (file.startsWith("note_") && file.endsWith(".txt")) {
                noteFiles.add(file);
            }
        }
        adapter.notifyDataSetChanged();
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
