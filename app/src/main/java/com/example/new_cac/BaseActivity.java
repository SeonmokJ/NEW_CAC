package com.example.new_cac;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_base);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupBottomNavigation();

    }

    private void setupBottomNavigation() {
        BottomNavigationView navBar = findViewById(R.id.bottomNavigationView);
        Menu menu = navBar.getMenu();
        navBar.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.navHome) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if (id == R.id.navBack) {
                finish();
                return true;
            } else if (id == R.id.navTimer) {
                startActivity(new Intent(this, Timer.class));
                return true;
            } else if (id == R.id.navLibrary) {
                startActivity(new Intent(this, TeachLIB.class));
                return true;
            } else if (id == R.id.navVault) {
                startActivity(new Intent(this, Timer.class));
                return true;
            }
            return false;
        });
}

    protected void setActivityLayout(int layoutResID){
        getLayoutInflater().inflate(layoutResID, findViewById(R.id.content_frame), true);
    }
}
