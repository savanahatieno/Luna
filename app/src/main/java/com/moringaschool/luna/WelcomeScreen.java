package com.moringaschool.luna;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import java.util.Objects;

public class WelcomeScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        long WELCOME_SCREEN_TIMEOUT = 0;
        new Handler().postDelayed(new Runnable() {
       @Override
       public void run() {
           Intent intent = new Intent(WelcomeScreen.this, MainActivity.class);
           startActivity(intent);
           finish();
       }
   },WELCOME_SCREEN_TIMEOUT);

    }
}