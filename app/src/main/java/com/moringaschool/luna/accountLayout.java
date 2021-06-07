package com.moringaschool.luna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class accountLayout extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {





    private FirebaseUser user;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;


    private String userid;


    private Button logout;


    //Drawer Menu Variables
    DrawerLayout drawerLayout4;
    NavigationView navigationView4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_layout);




        logout = (Button) findViewById(R.id.logout);


//
//        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
//        if (signInAccount != null){
//            full_name_profile.setTextInputAccessibilityDelegate(signInAccount.getGivenName());
//            email_profile_profile.setTextInputAccessibilityDelegate(signInAccount.getEmail());
//        }

        logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(accountLayout.this, Login.class));
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
                Toast.makeText(accountLayout.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });




        //Navigation Drawer IDS
        drawerLayout4 = findViewById(R.id.drawer_layout4);
        navigationView4 = findViewById(R.id.nav_view4);


        //Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar_account);
        setSupportActionBar(toolbar);



        //navigation drawer menu pop up
        navigationView4.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout4,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout4.addDrawerListener(toggle);
        toggle.syncState();

        navigationView4.setNavigationItemSelectedListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent intent = new Intent(accountLayout.this,TodoMain.class);
                startActivity(intent);
                break;
            case R.id.nav_inbox:
                Intent intent1 = new Intent(accountLayout.this,inboxLayout.class);
                startActivity(intent1);
                break;
            case R.id.nav_today:
                Intent intent2 = new Intent(accountLayout.this,todayLayout.class);
                startActivity(intent2);
                break;
            case R.id.nav_projects:
                Intent intent3 = new Intent(accountLayout.this,projectLayout.class);
                startActivity(intent3);
                break;
            case R.id.nav_account:
                Intent intent4 = new Intent(accountLayout.this,accountLayout.class);
                startActivity(intent4);
                break;
            case R.id.nav_share: Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show(); break;
        }
        drawerLayout4.closeDrawer(GravityCompat.START); return true;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.menu_search, menu);
//
//        MenuItem menuItem = menu.findItem(R.id.action_search);
//        final SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.setQueryHint("Search Here");
//
//        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Toast.makeText(accountLayout.this, query, Toast.LENGTH_SHORT).show();
//                searchView.clearFocus();
//                return true;
//            }
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return true;
//            }
//        };
//
//        searchView.setOnQueryTextListener(queryTextListener);
//
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public void onBackPressed() {
        if (drawerLayout4.isDrawerOpen(GravityCompat.START)){
            drawerLayout4.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}