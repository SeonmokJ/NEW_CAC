package com.example.new_cac;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TeachLIB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.teach_lib_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button teachingLIBmath = findViewById(R.id.teachLIBmath);
        teachingLIBmath.setOnClickListener(v -> {
            Intent math = new Intent(TeachLIB.this, TeachLIBmath.class);
            startActivity(math);});

        Button teachingLIBenglish = findViewById(R.id.teachLIBenglish);
        teachingLIBenglish.setOnClickListener(v -> {
            Intent english = new Intent(TeachLIB.this, TeachLIBenglish.class);
            startActivity(english);});

        Button teachingLIBscience = findViewById(R.id.teachLIBscience);
        teachingLIBscience.setOnClickListener(v -> {
            Intent science = new Intent(TeachLIB.this, TeachLIBscience.class);
            startActivity(science);});

        Button teachingLIBhistory = findViewById(R.id.teachLIBhistory);
        teachingLIBhistory.setOnClickListener(v -> {
            Intent history = new Intent(TeachLIB.this, TeachLIBhistory.class);
            startActivity(history);});

        Button teachingLIBcoding = findViewById(R.id.teachLIBcoding);
        teachingLIBcoding.setOnClickListener(v -> {
            Intent coding = new Intent(TeachLIB.this, TeachLIBcoding.class);
            startActivity(coding);});
    }
}
