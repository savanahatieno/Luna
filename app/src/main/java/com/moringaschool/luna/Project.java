package com.moringaschool.luna;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Project extends AppCompatActivity {

//
//    //Drawer Menu Variables
//    DrawerLayout drawerLayoutProject;
//    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);


//        drawerLayoutProject = findViewById(R.id.drawer_layoutproject);
//        navigationView = findViewById(R.id.nav_project);



        androidx.appcompat.widget.Toolbar toolbar= findViewById(R.id.toolbarproject);
        setSupportActionBar(toolbar);
//
//        //navigation drawer menu pop up
//        navigationView.bringToFront();
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayoutProject,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
//        drawerLayoutProject.addDrawerListener(toggle);
//        toggle.syncState();
//
//        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);




    }

    //To avoid application closing when pressing the back button
//    @Override
//    public void onBackPressed() {
//        if (drawerLayoutProject.isDrawerOpen(GravityCompat.START)){
//            drawerLayoutProject.closeDrawer(GravityCompat.START);
//        }
//        else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        return true;
//    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){

        getMenuInflater().inflate(R.menu.menu_search,menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search here");

       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               return false;
           }

           @Override
           public boolean onQueryTextChange(String newText) {

               return true;
           }
       });

        return  super.onCreateOptionsMenu(menu);
    }

}