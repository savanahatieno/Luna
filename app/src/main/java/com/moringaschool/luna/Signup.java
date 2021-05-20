package com.moringaschool.luna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Signup extends AppCompatActivity {

    private Button alreadybutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        alreadybutton = (Button) findViewById(R.id.alreadybuton);
        alreadybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

    }
    public void openLogin (){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
}