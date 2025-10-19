package com.example.new_cac;

public class Achievement {
    private String title;
    private String description;
    private boolean unlocked;

    // Constructor (sets up a new achievement)
    public Achievement(String title, String description, boolean unlocked) {
        this.title = title;
        this.description = description;
        this.unlocked = unlocked;
    }

    // Getters — used to read each variable from outside
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isUnlocked() {
        return unlocked;
    }
}
