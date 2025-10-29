package com.example.new_cac;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TeachLIBhistory extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setActivityLayout(R.layout.teach_lib_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button worlds = findViewById(R.id.worlds);
        Button ush = findViewById(R.id.ush);
        Button gov = findViewById(R.id.gov);
        worlds.setOnClickListener(v -> {
            startActivity(new Intent(TeachLIBhistory.this, LIBworlds.class));});
        ush.setOnClickListener(v -> {
            startActivity(new Intent(TeachLIBhistory.this, LIBush.class));});
        gov.setOnClickListener(v -> {
            startActivity(new Intent(TeachLIBhistory.this, LIBgov.class));});

    }
}