
package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Button;


public class GameActivity extends AppCompatActivity {

    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button quit = findViewById(R.id.quit);
        quit.setOnClickListener(unused -> startActivity(new Intent(this, MainActivity.class)));

        Button redCircle = findViewById(R.id.red_circle);
        redCircle.setOnClickListener(unused -> guessClicked(Constants.CircleColor.RED));

        Button orangeCircle = findViewById(R.id.orange_circle);
        orangeCircle.setOnClickListener(unused -> guessClicked(Constants.CircleColor.ORANGE));

        Button yellowCircle = findViewById(R.id.yellow_circle);
        yellowCircle.setOnClickListener(unused -> guessClicked(Constants.CircleColor.YELLOW));

        Button greenCircle = findViewById(R.id.green_circle);
        greenCircle.setOnClickListener(unused -> guessClicked(Constants.CircleColor.GREEN));

        Button blueCircle = findViewById(R.id.blue_circle);
        blueCircle.setOnClickListener(unused -> guessClicked(Constants.CircleColor.BLUE));

        Button purpleCircle = findViewById(R.id.purple_circle);
        purpleCircle.setOnClickListener(unused -> guessClicked(Constants.CircleColor.PURPLE));

        Button guess1 = findViewById(R.id.guess1);
        guess1.setOnClickListener(unused -> deleteGuess(Constants.Guess.GUESS1));

        Button guess2 = findViewById(R.id.guess2);
        guess2.setOnClickListener(unused -> deleteGuess(Constants.Guess.GUESS2));

        Button guess3 = findViewById(R.id.guess3);
        guess3.setOnClickListener(unused -> deleteGuess(Constants.Guess.GUESS3));

        Button guess4 = findViewById(R.id.guess4);
        guess4.setOnClickListener(unused -> deleteGuess(Constants.Guess.GUESS4));

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        phoneVibrate(5);
        phoneVibrate(10);
    }

    private void guessClicked(int color) {
        System.out.println("pressed circle: " + color);
    }


    private void deleteGuess(int choice) {
        System.out.println("pressed guess: " + choice);
    }


    /**
     * Uses the vibrator to create a vibration that repeats 3 times with the specified intensity.
     * @param intensity the intensity of vibration between 1 (weakest) and 10 (strongest).
     */

    private void phoneVibrate(int intensity) {

        int actualIntensity = 0;

        switch(intensity) {
            case 1: actualIntensity = 25;
            case 2: actualIntensity = 50;
            case 3: actualIntensity = 75;
            case 4: actualIntensity = 100;
            case 5: actualIntensity = 125;
            case 6: actualIntensity = 150;
            case 7: actualIntensity = 175;
            case 8: actualIntensity = 200;
            case 9: actualIntensity = 225;
            case 10: actualIntensity = 255;
            default: actualIntensity = 255;
        }

        long[] mVibratePattern = new long[]{0, 400, 400, 400, 400, 400, 400, 400};
        int[] mAmplitudes = new int[]{0, actualIntensity, 0, actualIntensity, 0, actualIntensity, 0, actualIntensity};

        if (vibrator.hasAmplitudeControl()) {
            VibrationEffect effect = VibrationEffect.createWaveform(mVibratePattern, mAmplitudes, -1);
            vibrator.vibrate(effect);
        }
    }
}