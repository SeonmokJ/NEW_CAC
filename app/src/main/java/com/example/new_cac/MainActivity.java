package com.example.new_cac;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        EdgeToEdge.enable(this);
        setContentView(R.layout.main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
=======

        setActivityLayout(R.layout.activity_main);


        Button teachingLibrary = findViewById(R.id.teachingLibrary);
        Button timer = findViewById(R.id.timer);

        teachingLibrary.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TeachLIB.class));});

        timer.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Timer.class));});

>>>>>>> seonmok
    }
}