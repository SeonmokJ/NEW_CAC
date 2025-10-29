package com.example.new_cac;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TeachLIBenglish extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setActivityLayout(R.layout.teach_lib_english);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button grammar = findViewById(R.id.grammar);
        Button ml = findViewById(R.id.media);
        Button ld = findViewById(R.id.literary);
        grammar.setOnClickListener(v -> {
            startActivity(new Intent(TeachLIBenglish.this, LIBgrammar.class));});
        ml.setOnClickListener(v -> {
            startActivity(new Intent(TeachLIBenglish.this, LIBmedialit.class));});
        ld.setOnClickListener(v -> {
            startActivity(new Intent(TeachLIBenglish.this, LIBliterary.class));});
    }
}