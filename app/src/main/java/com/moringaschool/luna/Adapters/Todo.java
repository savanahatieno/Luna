package com.moringaschool.luna.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.luna.Model.ToDoModel;
import com.moringaschool.luna.R;
import com.moringaschool.luna.inboxLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Todo extends RecyclerView.Adapter<Todo.ViewHolder>  {

    private List<ToDoModel> todoList;
    private inboxLayout inboxLayout_todo;



    public <E> Todo (inboxLayout inboxLayout_todo){
        this.inboxLayout_todo = inboxLayout_todo;
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


    public static  class ViewHolder extends  RecyclerView.ViewHolder{
        CheckBox task;

        ViewHolder(View view){
            super(view);
            task = view.findViewById(R.id.todoCheckBox);
        }
    }

}
