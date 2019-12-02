package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HelpPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_help);

        Button back = findViewById(R.id.back);
        back.setOnClickListener(unused -> startActivity(new Intent(this, MainActivity.class)));
    }
}
