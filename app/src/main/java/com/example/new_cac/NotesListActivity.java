package com.example.new_cac;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NotesListActivity extends BaseActivity {

    private ListView notesListView;
    private ArrayList<String> noteFiles;
    private NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityLayout(R.layout.activity_notes_list);

        notesListView = findViewById(R.id.notesListView);

        noteFiles = new ArrayList<>();
        loadNotes();

        adapter = new NotesAdapter(this, noteFiles);
        notesListView.setAdapter(adapter);

        // Click on list item to open note details
        notesListView.setOnItemClickListener((parent, view, position, id) -> {
            String fileName = noteFiles.get(position);
            Intent intent = new Intent(NotesListActivity.this, NoteDetailActivity.class);
            intent.putExtra("fileName", fileName);
            startActivity(intent);
        });



    }

    private void loadNotes() {
        noteFiles.clear();
        String[] files = fileList();
        for (String file : files) {
            if (file.startsWith("note_") && file.endsWith(".txt")) {
                noteFiles.add(file);
            }
        }
    }
}
