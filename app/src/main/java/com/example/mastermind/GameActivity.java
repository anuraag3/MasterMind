
package com.example.mastermind;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class GameActivity extends AppCompatActivity {

    private Vibrator vibrator;

    private int numGuessesClicked;

    private boolean[] guessesChosen;

    private int[] guessesColor;

    private List<int[]> guessHistory;

    private int[] answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        numGuessesClicked = 0;
        guessesChosen = new boolean[4];

        guessesColor = new int[4];

        guessHistory = new ArrayList<>();
        answer = generateAnswer();

        Button quit = findViewById(R.id.quit);
        quit.setOnClickListener(unused -> startActivity(new Intent(this, MainActivity.class)));

        Button check = findViewById(R.id.check);
        check.setOnClickListener(unused -> checkGuess());

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

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    private void guessClicked(int color) {

        Button guess1 = findViewById(R.id.guess1);
        Button guess2 = findViewById(R.id.guess2);
        Button guess3 = findViewById(R.id.guess3);
        Button guess4 = findViewById(R.id.guess4);

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
    }

    private void deleteGuess(int choice) {

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

    private void checkGuess() {
        if (numGuessesClicked < 4) {
            return;
        }

        Button[][] buttons = new Button[10][4];

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                String buttonID = "row" + (i + 1) + "circle" + (j + 1);
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
            }
        }

        Button[][] results = new Button[11][4];

        for (int i = 0; i < results.length; i++) {
            for (int j = 0; j < results[i].length; j++) {
                String buttonID = "row" + i + "result" + (j + 1);
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                results[i][j] = findViewById(resID);
            }
        }

        Button guess1 = findViewById(R.id.guess1);
        Button guess2 = findViewById(R.id.guess2);
        Button guess3 = findViewById(R.id.guess3);
        Button guess4 = findViewById(R.id.guess4);
        numGuessesClicked = 0;

        guess1.setVisibility(View.INVISIBLE);
        guess2.setVisibility(View.INVISIBLE);
        guess3.setVisibility(View.INVISIBLE);
        guess4.setVisibility(View.INVISIBLE);

        // for making a copy of color array
        int[] temp = new int[4];

        // for resetting conditions
        for (int i = 0 ; i < guessesChosen.length; i++) {
            guessesChosen[i] = false;
            temp[i] = guessesColor[i];
        }

        guessHistory.add(temp);

        // displaying guessHistory
        for (int i = 0; i < guessHistory.size(); i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[9 - i][j].setBackground(setColor((guessHistory.get(i))[j]));
                buttons[9 - i][j].setVisibility(View.VISIBLE);
            }
        }

        // displaying results
        int numCorrect = findNumCorrect(temp) - findNumCorrectPosition(temp);
        int numCorrectPosition = findNumCorrectPosition(temp);

        for (int i = 0; i < numCorrectPosition; i++) {
            results[11 - guessHistory.size()][i].setVisibility(View.VISIBLE);
        }

        for (int i = 0; i < numCorrect; i++) {
            results[11 - guessHistory.size()][numCorrectPosition + i].setBackground(setColor(69));
            results[11 - guessHistory.size()][numCorrectPosition + i].setVisibility(View.VISIBLE);
        }

        // win condition
        if (numCorrectPosition == 4) {
            userWins(temp);
        } else if (guessHistory.size() == 10) {
            userLoses();
        } else {
            phoneVibrate(1);
        }
    }

    public int findNumCorrect(int[] guess) {
        int numCorrect = 0;
        boolean[] matchedPair = new boolean[4];

        for (int i : guess) {
            if ((i == answer[0]) && (!matchedPair[0])) {
                numCorrect++;
                matchedPair[0] = true;
            } else if ((i == answer[1]) && (!matchedPair[1])) {
                numCorrect++;
                matchedPair[1] = true;
            } else if ((i == answer[2]) && (!matchedPair[2])) {
                numCorrect++;
                matchedPair[2] = true;
            } else if ((i == answer[3]) && (!matchedPair[3])) {
                numCorrect++;
                matchedPair[3] = true;
            }
        }

        return numCorrect;
    }

    private int findNumCorrectPosition(int[] guess) {
        int numCorrectPosition = 0;

        for (int i = 0; i < answer.length; i++) {
            if (answer[i] == guess[i]) {
                numCorrectPosition++;
            }
        }

        return numCorrectPosition;
    }

    private void phoneVibrate(int count) {
        if (count == 1) {
            VibrationEffect effect = VibrationEffect.createWaveform(new long[] {0, 400, 400}, new int[] {0, 255, 0}, -1);
            vibrator.vibrate(effect);
        } else if (count == 3) {
            VibrationEffect effect = VibrationEffect.createWaveform(new long[] {0, 400, 400, 400, 400, 400, 400}, new int[] {0, 255, 0, 255, 0, 255, 0}, -1);
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
        Drawable white_circle = ResourcesCompat.getDrawable(res, R.drawable.white_circle, null);

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

        } else if (color == Constants.CircleColor.PURPLE) {
            return (purple_circle);
        } else {
            return (white_circle);
        }
    }

    private void userWins(int[] guess) {

        phoneVibrate(3);

        LayoutInflater inflater = LayoutInflater.from(this);
        ViewGroup container = findViewById(R.id.winLayout);
        View rootview = inflater.inflate(R.layout.win_display, container,false);

        Button[] winCircles = new Button[4];

        for (int i = 0; i < winCircles.length; i++) {
            String buttonID = "win" + (i + 1);
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            winCircles[i] = rootview.findViewById(resID);
        }


        for (int i = 0; i < guess.length; i++) {
            winCircles[i].setBackground(setColor(guess[i]));
        }

        TextView winMessage = rootview.findViewById(R.id.win_message);
        winMessage.setText("Number of Guesses: " + guessHistory.size());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(rootview);

        builder.setNegativeButton("Finish", null);
        builder.setOnDismissListener(unused -> finish());
        builder.create().show();
    }

    private void userLoses() {

        phoneVibrate(3);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("You Lose. Try Again.");

        builder.setNegativeButton("Finish", null);
        builder.setOnDismissListener(unused -> finish());

        builder.create().show();
    }

    private int[] generateAnswer() {
        int[] toReturn = new int[4];

        for (int i = 0; i < toReturn.length; i++) {
            toReturn[i] = (int)(Math.random() * 6);
            System.out.print(toReturn[i] + " ");
        }

        return toReturn;
    }
}