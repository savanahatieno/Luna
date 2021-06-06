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
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class projectLayout extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //Drawer Menu Variables
    DrawerLayout drawerLayout3;
    NavigationView navigationView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_layout);


        //Navigation Drawer IDS
        drawerLayout3 = findViewById(R.id.drawer_layout3);
        navigationView3 = findViewById(R.id.nav_view3);


        //Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar_project);
        setSupportActionBar(toolbar);





        //navigation drawer menu pop up
        navigationView3.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout3,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout3.addDrawerListener(toggle);
        toggle.syncState();

        navigationView3.setNavigationItemSelectedListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent intent = new Intent(projectLayout.this,TodoMain.class);
                startActivity(intent);
                break;
            case R.id.nav_inbox:
                Intent intent1 = new Intent(projectLayout.this,inboxLayout.class);
                startActivity(intent1);
                break;
            case R.id.nav_today:
                Intent intent2 = new Intent(projectLayout.this,todayLayout.class);
                startActivity(intent2);
                break;
            case R.id.nav_projects:
                Intent intent3 = new Intent(projectLayout.this,projectLayout.class);
                startActivity(intent3);
                break;
            case R.id.nav_account:
                Intent intent4 = new Intent(projectLayout.this,accountLayout.class);
                startActivity(intent4);
                break;
            case R.id.nav_share: Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show(); break;
        }
        drawerLayout3.closeDrawer(GravityCompat.START); return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Here");

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(projectLayout.this, query, Toast.LENGTH_SHORT).show();
                searchView.clearFocus();
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        };

        searchView.setOnQueryTextListener(queryTextListener);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout3.isDrawerOpen(GravityCompat.START)){
            drawerLayout3.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}