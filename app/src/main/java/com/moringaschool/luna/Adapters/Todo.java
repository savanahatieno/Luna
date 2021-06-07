package com.moringaschool.luna.Adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.moringaschool.luna.AddNewTask;
import com.moringaschool.luna.Model.ToDoModel;
import com.moringaschool.luna.R;
import com.moringaschool.luna.inboxLayout;
import com.moringaschool.luna.projectLayout;
import com.moringaschool.luna.todayLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Todo extends RecyclerView.Adapter<Todo.ViewHolder>  {


    private List<ToDoModel> todoList;
    private inboxLayout inboxLayout_todo;
    private todayLayout todayLayout_todo;
    private projectLayout projectLayout_todo;

    private inboxLayout inboxLayout;



    public <E> Todo (inboxLayout inboxLayout_todo){
        this.inboxLayout_todo = inboxLayout_todo;
    }
    public <E> Todo (todayLayout todayLayout_todo){
        this.todayLayout_todo = todayLayout_todo;
    }
    public <E> Todo (projectLayout projectLayout_todo){
        this.projectLayout_todo = projectLayout_todo;
    }


    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout, parent, false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Todo.ViewHolder holder, int position) {
        final ToDoModel item = todoList.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    private boolean toBoolean(int n) {
        return n != 0;
    }




    public void setTasks (List<ToDoModel> todoList){
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        ToDoModel item = todoList.get(position);
        todoList.remove(position);
        notifyItemRemoved(position);
    }


    public void editItem(int position) {
        ToDoModel item = todoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(inboxLayout.getSupportFragmentManager(), AddNewTask.TAG);
    }


    public static  class ViewHolder extends  RecyclerView.ViewHolder{
        CheckBox task;

        ViewHolder(View view){
            super(view);
            task = view.findViewById(R.id.todoCheckBox);
        }
    }

}
