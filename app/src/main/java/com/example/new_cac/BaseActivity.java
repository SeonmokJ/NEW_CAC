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

        int[][] states = new int[][] {
                new int[] { android.R.attr.state_checked }, // selected
                new int[] { -android.R.attr.state_checked } // unselected
        };

        int[] colors = new int[] {
                getResources().getColor(R.color.selectedColor),   // selected color
                getResources().getColor(R.color.unselectedColor)  // unselected color
        };

// Create a ColorStateList
        ColorStateList colorStateList = new ColorStateList(states, colors);

// Apply it to icons and text
        navBar.setItemIconTintList(colorStateList);
        navBar.setItemTextColor(colorStateList);
//-------------------------------------------------------------
        navBar.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.navHome) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if (id == R.id.navSettings) {
                startActivity(new Intent(this, Settings.class));
                return true;
            } else if (id == R.id.navBack) {
                finish();
                return true;
            } else if (id == R.id.navAchievement) {
                startActivity(new Intent(this, AchievementActivity.class));
                return true;
            }
            return false;
        });
}

    protected void setActivityLayout(int layoutResID){
        getLayoutInflater().inflate(layoutResID, findViewById(R.id.content_frame), true);
    }
}
