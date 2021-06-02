package com.moringaschool.luna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText eUsername2, eEmail2, ePassword2;


    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private Button loginbutton;


    //CHECKBOX BUTTTON
    @BindView(R.id.checkboxlogin)
    CheckBox checkboxlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginbutton = (Button) findViewById(R.id.loginButton);
        loginbutton.setOnClickListener(this);

        //CHECKBOX HIDE AND SHOW
        checkboxlogin.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                ePassword2.setTransformationMethod(null);
            } else {
                ePassword2.setTransformationMethod(new PasswordTransformationMethod());
            }
        });


        eUsername2 = findViewById(R.id.username2);
        eEmail2 = findViewById(R.id.emailaddress2);
        ePassword2 = findViewById(R.id.password2);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        mAuth = FirebaseAuth.getInstance();


    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.banner:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.loginButton:
                loginbutton();
                break;
        }
    }

    private void loginbutton() {

        String username2 = eUsername2.getText().toString().trim();
        String email2 = eEmail2.getText().toString().trim();
        String password2 = ePassword2.getText().toString().trim();


        if (username2.isEmpty()) {
            eUsername2.setError("Username is required");
            eUsername2.requestFocus();
            return;
        }


        if (email2.isEmpty()) {
            eEmail2.setError("Email is required");
            eEmail2.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email2).matches()) {
            eEmail2.setError("Please provide valid email");
            eEmail2.requestFocus();
            return;
        }
        if (password2.isEmpty()) {
            ePassword2.setError("Password is required");
            ePassword2.requestFocus();
            return;
        }
        if (password2.length() < 6) {
            ePassword2.setError("Min password length should be 6 characters");
            ePassword2.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email2, password2).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                //redirect to user profile
                startActivity(new Intent(Login.this,ProfileActivity.class));
            } else {
                Toast.makeText(Login.this, "Failed to login! PLease check your credentials", Toast.LENGTH_SHORT).show();
            }

        });


    }
//
//    private void isUser() {
//        final String userEnteredUsername = eUsername2.getText().toString().trim();
//        final String userEnteredEmail = eEmail2.getText().toString().trim();
//        final String userEnteredPassword = ePassword2.getText().toString().trim();
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//
//        Query checkUser = reference.orderByChild("eUsername2").equalTo(userEnteredUsername);
//
//
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//
//                if (snapshot.exists()){
//
//                    String passwordFromDB = snapshot.child(userEnteredUsername).child("ePassword2").getValue(String.class);
//
//                    if (passwordFromDB.equals(userEnteredPassword)){
//                        String usernameFromDB = snapshot.child(userEnteredUsername).child("username").getValue(String.class);
//                        String emailFromDB = snapshot.child(userEnteredEmail).child("eEmail2").getValue(String.class);
//
//                        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
//                        intent.putExtra("username", usernameFromDB);
//                        intent.putExtra("email", emailFromDB);
//                        intent.putExtra("password", passwordFromDB);
//                        startActivity(intent);
//
//                    }else {
//                        progressBar.setVisibility(View.GONE);
//                        ePassword2.setError("Wrong Password");
//                        ePassword2.requestFocus();
//                    }
//
//                }else {
//                    progressBar.setVisibility(View.GONE);
//                    eUsername2.setError("No such User exist");
//                    eUsername2.requestFocus();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });

    }

