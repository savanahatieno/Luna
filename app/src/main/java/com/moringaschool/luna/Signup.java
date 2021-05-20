package com.moringaschool.luna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class Signup extends AppCompatActivity {


    EditText eFullName,eUsername, eEmail, ePassword;
    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button alreadybutton = (Button) findViewById(R.id.alreadybutton);
        alreadybutton.setOnClickListener(v -> {
            //do something
            Intent intent = new Intent(Signup.this, Login.class);
            startActivity(intent);
        });



        eFullName = findViewById(R.id.fullname);
        eUsername = findViewById(R.id.username);
        eEmail = findViewById(R.id.emailaddress);
        ePassword = findViewById(R.id.password);


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Add Validation
        awesomeValidation.addValidation(this,R.id.fullname, RegexTemplate.NOT_EMPTY,R.string.invalid_fullname);
        awesomeValidation.addValidation(this,R.id.username, RegexTemplate.NOT_EMPTY,R.string.invalid_username);
        awesomeValidation.addValidation(this,R.id.emailaddress, RegexTemplate.NOT_EMPTY,R.string.invalid_emailaddress);
        awesomeValidation.addValidation(this,R.id.password, RegexTemplate.NOT_EMPTY,R.string.invalid_password);


        Button signupButton = (Button) findViewById(R.id.signupButton);


        signupButton.setOnClickListener(v -> {
            if (awesomeValidation.validate()){
                Toast.makeText(getApplicationContext()
                        ,"Form Validate Successfully...",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplicationContext()
                        ,"Validation Failed....",Toast.LENGTH_LONG).show();
            }
        });

    }
}