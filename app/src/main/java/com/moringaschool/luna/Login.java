package com.moringaschool.luna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity {

    EditText  eUsername2, eEmail2, ePassword2;

    @BindView(R.id.checkboxlogin) CheckBox checkboxlogin;
    @BindView(R.id.loginButton) Button loginbutton;


    AwesomeValidation awesomeValidation2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


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


        awesomeValidation2 = new AwesomeValidation(ValidationStyle.BASIC);

        //Add Validation
        awesomeValidation2.addValidation(this,R.id.username2, RegexTemplate.NOT_EMPTY,R.string.invalid_username);
        awesomeValidation2.addValidation(this,R.id.emailaddress2, RegexTemplate.NOT_EMPTY,R.string.invalid_emailaddress);
        awesomeValidation2.addValidation(this,R.id.password2, RegexTemplate.NOT_EMPTY,R.string.invalid_password);




        loginbutton.setOnClickListener(v -> {
            if (awesomeValidation2.validate()){
                Toast.makeText(getApplicationContext()
                        ,"Form Validate Successfully...",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplicationContext()
                        ,"Validation Failed....",Toast.LENGTH_LONG).show();
            }
        });






    }
}