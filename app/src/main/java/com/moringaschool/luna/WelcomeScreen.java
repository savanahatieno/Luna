package com.moringaschool.luna;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class WelcomeScreen extends AppCompatActivity {

    private static final int WELCOME_SCREEN_TIMEOUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
    }
}