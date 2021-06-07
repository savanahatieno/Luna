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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {







    private FirebaseUser user;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;


    private String userid;


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
            startActivity(new Intent(ProfileActivity.this, Login.class));
        });



        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userid = user.getUid();

        final TextView fullnamefield = (TextView) findViewById(R.id.fullname_field);
        final TextView usernamefield = (TextView) findViewById(R.id.username_field);
        final TextInputLayout fullname_profile = (TextInputLayout) findViewById(R.id.fullnameprofile);
        final TextInputLayout username_profile = (TextInputLayout) findViewById(R.id.username_profile);
        final TextInputLayout email_profile = (TextInputLayout) findViewById(R.id.email_profile);


        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                User userp = snapshot.getValue(User.class);
                if (userp != null) {
                    String fullname = userp.fullname;
                    String username = userp.username;
                    String fullnameprofile = userp.fullname;
                    String usernameprofile = userp.username;
                    String emailprofile = userp.email;


                    fullnamefield.setText(fullname);
                    usernamefield.setText(username);
                    fullname_profile.getEditText().setText(fullnameprofile);
                    username_profile.getEditText().setText(usernameprofile);
                    email_profile.getEditText().setText(emailprofile);


                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });


    }



    }








