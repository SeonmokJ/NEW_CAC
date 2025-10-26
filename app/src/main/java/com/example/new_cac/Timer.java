package com.example.new_cac;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Timer extends BaseActivity {

    private EditText hoursInput, minutesInput, secondsInput;
    private TextView timerText;
    private Button startButton, stopButton;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private boolean isRunning = false;
    private boolean userIsEditing = false; // prevents loop when we programmatically update text

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityLayout(R.layout.timer);

        // link XML
        hoursInput = findViewById(R.id.hoursInput);
        minutesInput = findViewById(R.id.minutesInput);
        secondsInput = findViewById(R.id.secondsInput);
        timerText = findViewById(R.id.timerText);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);

        // listeners for start/stop
        startButton.setOnClickListener(v -> startTimer());
        stopButton.setOnClickListener(v -> stopTimer());

        // automatically update timer when user edits any input
        addAutoUpdateListener(hoursInput);
        addAutoUpdateListener(minutesInput);
        addAutoUpdateListener(secondsInput);
    }

    // Adds a text watcher to an input field
    private void addAutoUpdateListener(EditText input) {
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (userIsEditing) return;
                if (isRunning) {
                    restartTimerWithNewInput();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void startTimer() {
        if (isRunning) return;

        timeLeftInMillis = getInputTimeMillis();
        if (timeLeftInMillis <= 0) return;

        startCountDown(timeLeftInMillis);
    }

    private void restartTimerWithNewInput() {
        if (countDownTimer != null) countDownTimer.cancel();

        timeLeftInMillis = getInputTimeMillis();
        if (timeLeftInMillis > 0) {
            startCountDown(timeLeftInMillis);
        }
    }

    private void startCountDown(long timeMillis) {
        countDownTimer = new CountDownTimer(timeMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                isRunning = false;
                timerText.setText("Time's up!");
            }
        }.start();
        isRunning = true;
    }

    private void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            isRunning = false;
        }
    }

    // Converts user input (HH:MM:SS) → milliseconds
    private long getInputTimeMillis() {
        int hours = parseInput(hoursInput);
        int minutes = parseInput(minutesInput);
        int seconds = parseInput(secondsInput);
        return (hours * 3600L + minutes * 60L + seconds) * 1000L;
    }

    private int parseInput(EditText editText) {
        String text = editText.getText().toString().trim();
        if (text.isEmpty()) return 0;
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    // Updates timer display (HH:MM:SS)
    private void updateTimerText() {
        int hours = (int) (timeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((timeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String formatted = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timerText.setText(formatted);
    }
}
