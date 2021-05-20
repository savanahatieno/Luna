package com.moringaschool.luna;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;


import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import butterknife.BindView;

public class Login extends AppCompatActivity {

    EditText eUsername1, eEmail1, ePassword1;
    AwesomeValidation awesomeValidation;

    @BindView(R.id.checkboxlogin) CheckBox checkboxlogin;


    private Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        checkboxlogin.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)
            {
                ePassword1.setTransformationMethod(null);
            }
            else
            {
                ePassword1.setTransformationMethod(new PasswordTransformationMethod());
            }
        });




        eUsername1 = findViewById(R.id.username1);
        eEmail1 = findViewById(R.id.emailaddress1);
        ePassword1 = findViewById(R.id.password1);


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Add Validation

        awesomeValidation.addValidation(this,R.id.username1, RegexTemplate.NOT_EMPTY,R.string.invalid_username);
        awesomeValidation.addValidation(this,R.id.emailaddress1, RegexTemplate.NOT_EMPTY,R.string.invalid_emailaddress);
        awesomeValidation.addValidation(this,R.id.password1, RegexTemplate.NOT_EMPTY,R.string.invalid_password);

        loginButton.setOnClickListener(v -> {
            if (awesomeValidation.validate()){
                Toast.makeText(getApplicationContext()
                ,"Form Validate Successfully...",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplicationContext()
                ,"Validation Failed....",Toast.LENGTH_LONG).show();
            }
        });

        loginButton = (Button) findViewById(R.id.signupButton);

        loginButton.setOnClickListener(v -> {
            //do something
            Toast.makeText(Login.this, "Account Created", Toast.LENGTH_LONG).show();
        });


    }

    }