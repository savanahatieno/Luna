package com.moringaschool.luna;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class Login extends AppCompatActivity {

    EditText eUsername, eEmail, ePassword;
    AwesomeValidation awesomeValidation;


    private Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        eUsername = findViewById(R.id.username);
        eEmail = findViewById(R.id.emailaddress);
        ePassword = findViewById(R.id.password);


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Add Validation

        awesomeValidation.addValidation(this,R.id.username, RegexTemplate.NOT_EMPTY,R.string.invalid_username);
        awesomeValidation.addValidation(this,R.id.emailaddress, RegexTemplate.NOT_EMPTY,R.string.invalid_emailaddress);
        awesomeValidation.addValidation(this,R.id.password, RegexTemplate.NOT_EMPTY,R.string.invalid_password);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()){
                    Toast.makeText(getApplicationContext()
                    ,"Form Validate Successfully...",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext()
                    ,"Validation Failed....",Toast.LENGTH_LONG).show();
                }
            }
        });

        loginButton = (Button) findViewById(R.id.signupButton);

        loginButton.setOnClickListener(v -> {
            //do something
            Toast.makeText(Login.this, "Account Created", Toast.LENGTH_LONG).show();
        });


    }

    }