package com.moringaschool.luna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.moringaschool.luna.Adapters.ToDoAdapter;
import com.moringaschool.luna.Model.ToDoModel;
import com.moringaschool.luna.Utilis.DatabaseHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TodoMain extends AppCompatActivity implements DialogCloseListener, NavigationView.OnNavigationItemSelectedListener {

    private DatabaseHandler db;

    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;
    private FloatingActionButton fab ;

    private List <ToDoModel> taskList;

    //Drawer Menu Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_main);



        //Navigation Drawer IDS
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);



        db = new DatabaseHandler(this);
        db.openDatabase();


        //Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //navigation drawer menu pop up
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

       navigationView.setNavigationItemSelectedListener(this);
       taskList = new ArrayList<>() ;

        tasksRecyclerView = findViewById(R.id.taskRecyclerVIew);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new ToDoAdapter(db,TodoMain.this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        fab = findViewById(R.id.fab);

        taskList = db.getAllTasks();
        Collections.reverse(taskList);


        tasksAdapter.setTasks(taskList);


        fab.setOnClickListener((View v) -> AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG));

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent intent = new Intent(TodoMain.this,TodoMain.class);
                startActivity(intent);
                break;
            case R.id.nav_inbox:
               Intent intent1 = new Intent(TodoMain.this,inboxLayout.class);
               startActivity(intent1);
                break;
            case R.id.nav_today:
                Intent intent2 = new Intent(TodoMain.this,todayLayout.class);
                startActivity(intent2);
                break;
            case R.id.nav_projects:
                Intent intent3 = new Intent(TodoMain.this,projectLayout.class);
                startActivity(intent3);
                break;
            case R.id.nav_account:
                Intent intent4 = new Intent(TodoMain.this,accountLayout.class);
                startActivity(intent4);
                break;
            case R.id.nav_share: Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show(); break;
        }
        drawerLayout.closeDrawer(GravityCompat.START); return true;
    }


    @Override
    public void handleDialogClose(DialogInterface dialog){
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);
        tasksAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        final androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Here");

        androidx.appcompat.widget.SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(TodoMain.this, query, Toast.LENGTH_SHORT).show();
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

    //To avoid application closing when pressing the back button
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