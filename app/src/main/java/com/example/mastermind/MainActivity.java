package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newGame = findViewById(R.id.newGame);
        newGame.setOnClickListener(v -> startActivity(new Intent(this, GameActivity.class)));

        Button help = findViewById(R.id.help);
        help.setOnClickListener(v -> startActivity(new Intent(this, HelpPage.class)));
    }
}
