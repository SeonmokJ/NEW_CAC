package com.example.new_cac;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TeachLIBscience extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setActivityLayout(R.layout.teach_lib_science);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button bio = findViewById(R.id.bio);
        Button chem = findViewById(R.id.chem);
        Button physics = findViewById(R.id.physics);
        bio.setOnClickListener(v -> {
            startActivity(new Intent(TeachLIBscience.this, LIBbio.class));});
        chem.setOnClickListener(v -> {
            startActivity(new Intent(TeachLIBscience.this, LIBchem.class));});
        physics.setOnClickListener(v -> {
            startActivity(new Intent(TeachLIBscience.this, LIBphysics.class));});

    }
}