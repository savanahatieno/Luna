package com.moringaschool.luna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class StartPage extends AppCompatActivity {

   //Initialize variable
    Button btngoogleSignIn;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        //Assign variable
        btngoogleSignIn = findViewById(R.id.google_signIn);

        //Initialize sign in options
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN
        ).requestIdToken("348534258197-gaa3sdlb1a6c5t96961j461e228egl53.apps.googleusercontent.com")
                .requestEmail()
                .build();

        //initialize sign in client
        googleSignInClient = GoogleSignIn.getClient(StartPage.this
                            ,googleSignInOptions);
        btngoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize sign in intent
                Intent intent = googleSignInClient.getSignInIntent();
                //Start activity for result
                StartActivityForResult(intent);
            }
        });





    }

    private void StartActivityForResult(Intent intent) {
    }


}