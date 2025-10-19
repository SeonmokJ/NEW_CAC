package com.example.new_cac;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AchievementAdapter extends BaseAdapter {

    private Context context;
    private List<Achievement> achievements;

    public AchievementAdapter(Context context, List<Achievement> achievements) {
        this.context = context;
        this.achievements = achievements;
    }

    @Override
    public int getCount() {
        return achievements.size();
    }

    @Override
    public Object getItem(int position) {
        return achievements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // This creates one "view" for each achievement item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_achievement, parent, false);
        }

        Achievement achievement = achievements.get(position);

        TextView title = convertView.findViewById(R.id.achievementTitle);
        TextView desc = convertView.findViewById(R.id.achievementDesc);
        ImageView icon = convertView.findViewById(R.id.achievementIcon);

        title.setText(achievement.getTitle());
        desc.setText(achievement.getDescription());

        // Style depending on locked/unlocked
        if (achievement.isUnlocked()) {
            icon.setColorFilter(Color.parseColor("#FFD700")); // gold color for unlocked
            convertView.setAlpha(1.0f);
        } else {
            icon.setColorFilter(Color.GRAY); // gray for locked
            convertView.setAlpha(0.5f);
        }

        return convertView;
    }
}
