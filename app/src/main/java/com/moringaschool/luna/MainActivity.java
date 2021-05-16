package com.moringaschool.luna;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    //Variables
    Animation topAnim, bottomAnim;
    ImageView imageView;
    TextView textView1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //HOOKS
        imageView = findViewById(R.id.imageview);
        textView1 = findViewById(R.id.textView1);


        imageView.setAnimation(topAnim);
        textView1.setAnimation(bottomAnim);




    }


}