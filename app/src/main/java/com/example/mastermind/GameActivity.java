
package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;


public class GameActivity extends AppCompatActivity {

    private Vibrator vibrator;

    private int numGuessesClicked;

    private boolean[] guessesChosen;

    private int[] guessesColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        numGuessesClicked = 0;
        guessesChosen = new boolean[4];
        guessesColor = new int[4];

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
        Button guess2 = findViewById(R.id.guess2);
        Button guess3 = findViewById(R.id.guess3);
        Button guess4 = findViewById(R.id.guess4);

        guess1.setOnClickListener(unused -> deleteGuess(Constants.Guess.GUESS1));
        guess2.setOnClickListener(unused -> deleteGuess(Constants.Guess.GUESS2));
        guess3.setOnClickListener(unused -> deleteGuess(Constants.Guess.GUESS3));
        guess4.setOnClickListener(unused -> deleteGuess(Constants.Guess.GUESS4));

        guess1.setVisibility(View.INVISIBLE);
        guess2.setVisibility(View.INVISIBLE);
        guess3.setVisibility(View.INVISIBLE);
        guess4.setVisibility(View.INVISIBLE);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        phoneVibrate(5);
        phoneVibrate(10);
    }

    private void guessClicked(int color) {
        System.out.println("pressed circle: " + color);

        Button guess1 = findViewById(R.id.guess1);
        Button guess2 = findViewById(R.id.guess2);
        Button guess3 = findViewById(R.id.guess3);
        Button guess4 = findViewById(R.id.guess4);
        /*
        if (numGuessesClicked == 0) {
            guess1.setBackground(setColor(color));
            guess1.setVisibility(View.VISIBLE);

            numGuessesClicked++;
            guessesChosen[0] = true;
            guessesColor[0] = color;

        } else if (numGuessesClicked == 1) {
            guess2.setBackground(setColor(color));
            guess2.setVisibility(View.VISIBLE);

            numGuessesClicked++;
            guessesChosen[1] = true;
            guessesColor[1] = color;

        } else if (numGuessesClicked == 2) {
            guess3.setBackground(setColor(color));
            guess3.setVisibility(View.VISIBLE);

            numGuessesClicked++;
            guessesChosen[2] = true;
            guessesColor[2] = color;

        } else if (numGuessesClicked == 3) {
            guess4.setBackground(setColor(color));
            guess4.setVisibility(View.VISIBLE);

            numGuessesClicked++;
            guessesChosen[3] = true;
            guessesColor[3] = color;

        } else {*/
        for (int i = 0; i < guessesChosen.length; i++) {
            if (!guessesChosen[i]) {
                if (i == 0) {
                    guess1.setBackground(setColor(color));
                    guess1.setVisibility(View.VISIBLE);

                    numGuessesClicked++;
                    guessesChosen[0] = true;
                    guessesColor[0] = color;
                    break;

                } else if (i == 1) {
                    guess2.setBackground(setColor(color));
                    guess2.setVisibility(View.VISIBLE);

                    numGuessesClicked++;
                    guessesChosen[1] = true;
                    guessesColor[1] = color;
                    break;

                } else if (i == 2) {
                    guess3.setBackground(setColor(color));
                    guess3.setVisibility(View.VISIBLE);

                    numGuessesClicked++;
                    guessesChosen[2] = true;
                    guessesColor[2] = color;
                    break;

                } else {
                    guess4.setBackground(setColor(color));
                    guess4.setVisibility(View.VISIBLE);

                    numGuessesClicked++;
                    guessesChosen[3] = true;
                    guessesColor[3] = color;
                    break;
                }
            }
        }
        //}
    }

    private void deleteGuess(int choice) {
        System.out.println("pressed guess: " + choice);

        Button guess1 = findViewById(R.id.guess1);
        Button guess2 = findViewById(R.id.guess2);
        Button guess3 = findViewById(R.id.guess3);
        Button guess4 = findViewById(R.id.guess4);

        if (choice <= numGuessesClicked) {
            if (choice == 1) {
                guess1.setVisibility(View.INVISIBLE);
                guessesChosen[0] = false;

            } else if (choice == 2) {
                guess2.setVisibility(View.INVISIBLE);
                guessesChosen[1] = false;

            } else if (choice == 3) {
                guess3.setVisibility(View.INVISIBLE);
                guessesChosen[2] = false;

            } else {
                guess4.setVisibility(View.INVISIBLE);
                guessesChosen[3] = false;
            }
        }
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

    private Drawable setColor(int color) {

        Resources res = getResources();
        Drawable red_circle = ResourcesCompat.getDrawable(res, R.drawable.red_circle, null);
        Drawable orange_circle = ResourcesCompat.getDrawable(res, R.drawable.orange_circle, null);
        Drawable yellow_circle = ResourcesCompat.getDrawable(res, R.drawable.yellow_circle, null);
        Drawable green_circle = ResourcesCompat.getDrawable(res, R.drawable.green_circle, null);
        Drawable blue_circle = ResourcesCompat.getDrawable(res, R.drawable.blue_circle, null);
        Drawable purple_circle = ResourcesCompat.getDrawable(res, R.drawable.purple_circle, null);

        if (color == Constants.CircleColor.RED) {
            return (red_circle);

        } else if (color == Constants.CircleColor.ORANGE) {
            return (orange_circle);

        } else if (color == Constants.CircleColor.YELLOW) {
            return (yellow_circle);

        } else if (color == Constants.CircleColor.GREEN) {
            return (green_circle);

        } else if (color == Constants.CircleColor.BLUE) {
            return (blue_circle);

        } else {
            return (purple_circle);
        }
    }
}