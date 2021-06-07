package com.moringaschool.luna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.moringaschool.luna.Adapters.Todo;
import com.moringaschool.luna.ApiUser.projects;
import com.moringaschool.luna.Model.ToDoModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class projectLayout extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{



    private RecyclerView recyclerView3;
    private Todo tasksadapter;

    private List<ToDoModel> taskList;
    //Drawer Menu Variables
    DrawerLayout drawerLayout3;
    NavigationView navigationView3;

    private TextView textViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_layout);

        textViewResult = findViewById(R.id.text_view_result);

//        //Retrofit
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.todoist.com/rest/v1/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        TodoistApi todoistApi = retrofit.create(TodoistApi.class);
//
//        Call<List<projects>>  call = TodoistApi.getprojects();
//
//
//        call.enqueue(new Callback<List<projects>>() {
//            @Override
//            public void onResponse(@NotNull Call<List<projects>> call, @NotNull Response<List<projects>> response) {
//
//                if (!response.isSuccessful()){
//                    textViewResult.setText("Code:" + response.code());
//                    return;
//                }
//
//                List<projects> projects1 = response.body();
//
//                for (projects projects: projects1){
//                    String contents = "";
//                    contents += "ID:" + projects.getId() + "\n";
//                    contents += "Name:" + projects.getName() + "\n";
//                    contents += "Comment:" + projects.getCommentCount() + "\n";
//                    contents += "Order:" + projects.getOrder() + "\n";
//
//                    textViewResult.append(contents);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<projects>> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
//            }
//        });


        taskList = new ArrayList<>();





        //RecyclerView
        recyclerView3 = findViewById(R.id.taskRecyclerView3);


        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager(new LinearLayoutManager(projectLayout.this));
        tasksadapter = new Todo(this);
        recyclerView3.setAdapter(tasksadapter);

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
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });



        FloatingActionButton floatingActionButton_delete = findViewById(R.id.floating_delete_button);
        floatingActionButton_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(projectLayout.this, "Item Deleted", Toast.LENGTH_SHORT).show();
            }
        });




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