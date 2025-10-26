package com.example.new_cac;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class NotesAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> notes;
    private LayoutInflater inflater;

    public NotesAdapter(Context context, ArrayList<String> notes) {
        this.context = context;
        this.notes = notes;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() { return notes.size(); }

    @Override
    public Object getItem(int position) { return notes.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.note_list_item, parent, false);
        }

        TextView noteName = convertView.findViewById(R.id.noteName);
        Button deleteBtn = convertView.findViewById(R.id.deleteBtn);

        final String fileName = notes.get(position);
        noteName.setText(fileName);

        // 1️⃣ Click on note name to open details page
        noteName.setOnClickListener(view -> {
            Intent intent = new Intent(context, NoteDetailActivity.class);
            intent.putExtra("fileName", fileName);
            context.startActivity(intent);
        });

        // 2️⃣ Delete button functionality
        deleteBtn.setOnClickListener(view -> {
            File file = new File(context.getFilesDir(), fileName);
            if (file.exists() && file.delete()) {
                notes.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Deleted " + fileName, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Failed to delete " + fileName, Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
