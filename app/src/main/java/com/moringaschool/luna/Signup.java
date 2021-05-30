package com.moringaschool.luna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class Signup extends AppCompatActivity implements View.OnClickListener{


    private FirebaseAuth mAuth;
    private TextView banner;

    EditText eFullName, eUsername, eEmail, ePassword;
    Button signupbutton;


    //Progress Bar
    private ProgressBar progressBar;

    @BindView(R.id.alreadybutton)
    Button alreadybutton;
    @BindView(R.id.checkboxsignup)
    CheckBox checkboxsignup;


    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);


        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        checkboxsignup.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                ePassword.setTransformationMethod(null);
            } else {
                ePassword.setTransformationMethod(new PasswordTransformationMethod());
            }
        });


        eFullName = findViewById(R.id.fullname);
        eUsername = findViewById(R.id.username);
        eEmail = findViewById(R.id.emailaddress);
        ePassword = findViewById(R.id.password);


//        //FIREBASE AUTHENTICATION
//        signupbutton.setOnClickListener(v -> {
//            String email = eEmail.getText().toString();
//            String pass = ePassword.getText().toString();
//
//            RegisterUser(email,pass);
//
//        });

//
//        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
//
//        //Add Validation
//        awesomeValidation.addValidation(this,R.id.fullname, RegexTemplate.NOT_EMPTY,R.string.invalid_fullname);
//        awesomeValidation.addValidation(this,R.id.username, RegexTemplate.NOT_EMPTY,R.string.invalid_username);
//        awesomeValidation.addValidation(this,R.id.emailaddress, RegexTemplate.NOT_EMPTY,R.string.invalid_emailaddress);
//        awesomeValidation.addValidation(this,R.id.password, RegexTemplate.NOT_EMPTY,R.string.invalid_password);


        Button signupButton = (Button) findViewById(R.id.signupButton);
        signupButton.setOnClickListener(this);


//        signupButton.setOnClickListener(v -> {
//            if (awesomeValidation.validate()){
//                Toast.makeText(getApplicationContext()
//                        ,"Form Validate Successfully...",Toast.LENGTH_LONG).show();
//            }else {
//                Toast.makeText(getApplicationContext()
//                        ,"Validation Failed....",Toast.LENGTH_LONG).show();
//            }
//        });

        alreadybutton.setOnClickListener(v -> {
            Intent intent = new Intent(Signup.this, Login.class);
            startActivity(intent);
        });

    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.banner:
            startActivity(new Intent(this,MainActivity.class));
            break;
        case R.id.signupButton:
            signupbutton();
            break;
    }
    }

    private void signupbutton() {
        String fullname = eFullName.getText().toString().trim();
        String username = eUsername.getText().toString().trim();
        String email = eEmail.getText().toString().trim();
        String password = ePassword.getText().toString().trim();

        if (fullname.isEmpty()){
            eFullName.setError("Full name is required");
            eFullName.requestFocus();
            return;
        }
        if (username.isEmpty()){
            eUsername.setError("Username is required");
            eUsername.requestFocus();
            return;
        }
        if (email.isEmpty()){
            eEmail.setError("Email is required");
            eEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            eEmail.setError("Please provide valid email");
            eEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            ePassword.setError("Password is required");
            ePassword.requestFocus();
            return;
        }
        if (password.length() < 6){
            ePassword.setError("Min password length should be 6 characters");
            ePassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {
                      if (task.isSuccessful()){
                          User user = new User(fullname, username, email);

                          FirebaseDatabase.getInstance().getReference("Users")
                                  .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                  .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                              @Override
                              public void onComplete(@NonNull Task<Void> task) {

                                  if (task.isSuccessful()){
                                      Toast.makeText(Signup.this,"Registered Successfully!!",Toast.LENGTH_SHORT).show();
                                      progressBar.setVisibility(View.VISIBLE);

                                      //redirect to login layout

                                  }else {
                                      Toast.makeText(Signup.this,"Failed to Register! Try Again!",Toast.LENGTH_SHORT).show();
                                      progressBar.setVisibility(View.VISIBLE);
                                  }

                              }
                          });
                      }else {
                          Toast.makeText(Signup.this,"Failed to Register",Toast.LENGTH_LONG).show();
                          progressBar.setVisibility(View.GONE);
                      }

                    }
                });
















    }

}

