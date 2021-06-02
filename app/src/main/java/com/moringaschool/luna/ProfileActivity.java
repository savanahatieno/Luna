package com.moringaschool.luna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static java.util.Objects.*;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;






    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView displaynameedit = (TextView) findViewById(R.id.displayfullname) ;
        final TextInputEditText fullnameedit = (TextInputEditText) findViewById(R.id.fullname_profile) ;
        final TextInputEditText usernameedit = (TextInputEditText) findViewById(R.id.username_profile) ;
        final TextInputEditText emailedit = (TextInputEditText) findViewById(R.id.emailprofile) ;


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    String fullname = userProfile.fullname;
                    String username= userProfile.username;
                    String email = userProfile.email;

                    displaynameedit.setText("Welcome, " + fullname + "!");
                    fullnameedit.setText(fullname);
                    usernameedit.setText(username);
                    emailedit.setText(email);

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this,"Something wrong happened ",Toast.LENGTH_SHORT).show();
            }
        });

        


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