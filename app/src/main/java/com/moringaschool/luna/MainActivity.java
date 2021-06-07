package com.moringaschool.luna;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    //Variables
    Animation topAnim, bottomAnim;
    ImageView imageView;
    TextView textView1;
    private long WELCOME_SCREEN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //HOOKS
        imageView = findViewById(R.id.imageview);
        textView1 = findViewById(R.id.textView1);


        imageView.setAnimation(topAnim);
        textView1.setAnimation(bottomAnim);


        final Handler handler = new Handler();
            handler.postDelayed(() -> {
                startActivity(new Intent(MainActivity.this,inboxLayout.class));
                finish();
            },4000);




    }
}