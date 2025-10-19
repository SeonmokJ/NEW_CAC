package com.example.new_cac;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class AchievementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.achievement);

        ListView listView = findViewById(R.id.achievementsList);

        // 1️⃣ Create example data
        List<Achievement> achievements = new ArrayList<>();
        achievements.add(new Achievement("First Launch", "Opened the app for the first time", true));
        achievements.add(new Achievement("Study Streak", "Studied 5 days in a row", false));
        achievements.add(new Achievement("Focus Master", "Completed 10 focus sessions", false));

        // 2️⃣ Create adapter and connect it
        AchievementAdapter adapter = new AchievementAdapter(this, achievements);
        listView.setAdapter(adapter);
    }
}
