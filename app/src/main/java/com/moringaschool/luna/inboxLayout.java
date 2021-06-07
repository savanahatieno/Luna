package com.moringaschool.luna;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.moringaschool.luna.Adapters.Todo;
import com.moringaschool.luna.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class inboxLayout extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener  {

    private RecyclerView recyclerView1;
    private Todo tasksadapter;

    private List<ToDoModel> taskList;
    private List<ToDoModel> taskListFull;


    //Drawer Menu Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox_layout);

        taskList = new ArrayList<>();





        //RecyclerView
        recyclerView1 = findViewById(R.id.taskRecyclerView1);


        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(inboxLayout.this));
        tasksadapter = new Todo(this);
        recyclerView1.setAdapter(tasksadapter);

        ToDoModel task = new ToDoModel();
        task.setTask("This is a new task created");
        task.setStatus(0);
        task.setId(1);

        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);

        tasksadapter.setTasks(taskList);





        //Floating action bar
        FloatingActionButton floatingActionButton_add = findViewById(R.id.floating_add_button);
        floatingActionButton_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        FloatingActionButton floatingActionButton_delete = findViewById(R.id.floating_delete_button);
        floatingActionButton_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        //Navigation Drawer IDS
        drawerLayout = findViewById(R.id.drawer_layout1);
        navigationView = findViewById(R.id.nav_view1);


        //Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar_inbox);
        setSupportActionBar(toolbar);





        //navigation drawer menu pop up
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent intent = new Intent(inboxLayout.this,TodoMain.class);
                startActivity(intent);
                break;
            case R.id.nav_inbox:
                Intent intent1 = new Intent(inboxLayout.this,inboxLayout.class);
                startActivity(intent1);
                break;
            case R.id.nav_today:
                Intent intent2 = new Intent(inboxLayout.this,todayLayout.class);
                startActivity(intent2);
                break;
            case R.id.nav_projects:
                Intent intent3 = new Intent(inboxLayout.this,projectLayout.class);
                startActivity(intent3);
                break;
            case R.id.nav_account:
                Intent intent4 = new Intent(inboxLayout.this,accountLayout.class);
                startActivity(intent4);
                break;
            case R.id.nav_share: Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show(); break;
        }
        drawerLayout.closeDrawer(GravityCompat.START); return true;
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
                Toast.makeText(inboxLayout.this, query, Toast.LENGTH_SHORT).show();
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
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}