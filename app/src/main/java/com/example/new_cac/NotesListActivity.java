package com.example.new_cac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NotesListActivity extends AppCompatActivity {

    private ListView notesListView;
    private ArrayList<String> noteFiles;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list); // XML layout for notes list

        notesListView = findViewById(R.id.notesListView);

        // Load all saved notes
        noteFiles = new ArrayList<>();
        String[] files = fileList();
        for (String file : files) {
            if (file.startsWith("note_") && file.endsWith(".txt")) {
                noteFiles.add(file);
            }
        }

        // Set adapter to display notes
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, noteFiles);
        notesListView.setAdapter(adapter);

        // Click a note to open its detail
        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fileName = noteFiles.get(position);
                Intent intent = new Intent(NotesListActivity.this, NoteDetailActivity.class);
                intent.putExtra("fileName", fileName);
                startActivity(intent);
            }
        });
    }
}
