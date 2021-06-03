package com.moringaschool.luna;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Tag;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

public class ProfileActivity extends AppCompatActivity {




    private FirebaseUser user;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;


    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);







        logout = (Button) findViewById(R.id.logout);


//
//        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
//        if (signInAccount != null){
//            full_name_profile.setTextInputAccessibilityDelegate(signInAccount.getGivenName());
//            email_profile_profile.setTextInputAccessibilityDelegate(signInAccount.getEmail());
//        }

        logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(ProfileActivity.this,Login.class));
        });


    }


}