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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;



import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText  eUsername2, eEmail2, ePassword2;

    @BindView(R.id.checkboxlogin) CheckBox checkboxlogin;


    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private Button loginbutton;


    AwesomeValidation awesomeValidation2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginbutton = (Button) findViewById(R.id.loginButton) ;
        loginbutton.setOnClickListener(this);


        checkboxlogin.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)
            {
                ePassword2.setTransformationMethod(null);
            }
            else
            {
                ePassword2.setTransformationMethod(new PasswordTransformationMethod());
            }
        });


        eUsername2 = findViewById(R.id.username2);
        eEmail2 = findViewById(R.id.emailaddress2);
        ePassword2 = findViewById(R.id.password2);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        mAuth = FirebaseAuth.getInstance();

//
//        awesomeValidation2 = new AwesomeValidation(ValidationStyle.BASIC);
//
//        //Add Validation
//        awesomeValidation2.addValidation(this,R.id.username2, RegexTemplate.NOT_EMPTY,R.string.invalid_username);
//        awesomeValidation2.addValidation(this,R.id.emailaddress2, RegexTemplate.NOT_EMPTY,R.string.invalid_emailaddress);
//        awesomeValidation2.addValidation(this,R.id.password2, RegexTemplate.NOT_EMPTY,R.string.invalid_password);
//
//
//
//
//        loginbutton.setOnClickListener(v -> {
//            if (awesomeValidation2.validate()){
//                Toast.makeText(getApplicationContext()
//                        ,"Form Validate Successfully...",Toast.LENGTH_LONG).show();
//            }else {
//                Toast.makeText(getApplicationContext()
//                        ,"Validation Failed....",Toast.LENGTH_LONG).show();
//            }
//        });




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

        mAuth.signInWithEmailAndPassword(email2,password2).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                //redirect to user profile
                startActivity(new Intent(Login.this,ProfileActivity.class));
            }else {
                Toast.makeText(Login.this,"Failed to login! PLease check your credentials",Toast.LENGTH_SHORT).show();
            }

        });










    }
}