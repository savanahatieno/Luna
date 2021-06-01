package com.moringaschool.luna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    TextView profile_name , profile_mail;

    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        

//
//        logout = (Button) findViewById(R.id.logout);
//        profile_name = findViewById(R.id.profile_name);
//        profile_mail = findViewById(R.id.profile_mail);
//
//
//        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
//        if (signInAccount != null){
//            profile_name.setText(signInAccount.getDisplayName());
//            profile_mail.setText(signInAccount.getEmail());
//        }
//
//        logout.setOnClickListener(v -> {
//            FirebaseAuth.getInstance().signOut();
//            startActivity(new Intent(ProfileActivity.this,Login.class));
//        });


    }

}