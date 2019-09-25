package com.example.hellowrold;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewTodoListActivity extends AppCompatActivity {
    private List<TodoItem> todoItemList = new ArrayList<>();
    private EditText inputTodo;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("RecylerView", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_todo_list_test);
        inputTodo = findViewById(R.id.todoInput);
        Button addTodoBtn = findViewById(R.id.addTodoBtn);
        addTodoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String todo = inputTodo.getText().toString();
                todoItemList.add(new TodoItem(todo));
                updateList();
                inputTodo.setText("");
            }
        });
        for (int i=1;i<=20;i++) {
            todoItemList.add(new TodoItem("------>" + i));
        }
//        updateList();
        recyclerView = findViewById(R.id.todoRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new TodoItemAdapter(todoItemList);
        recyclerView.setAdapter(adapter);
    }

    private void updateList() {
        TodoItemAdapter adapter = new TodoItemAdapter(todoItemList);
        RecyclerView recyclerView = findViewById(R.id.todoRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public class TodoItemAdapter extends RecyclerView.Adapter<TodoItemAdapter.ItemViewHolder> {

        private List<TodoItem> todoItemList;
        public TodoItemAdapter(List<TodoItem> todoItemList) {
            this.todoItemList = todoItemList;
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // 创建ViewHolder 绑定事件
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
            final ItemViewHolder holder = new ItemViewHolder(view);
            holder.completedBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    todoItemList.remove(position);
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            //设置属性
            final TodoItem item = todoItemList.get(position);
            Log.d("ITEM[]: " + Integer.toString(position), item.getTodo());
            holder.todo.setText(item.getTodo());
        }

        @Override
        public int getItemCount() {
            return this.todoItemList.size();
        }

        public class ItemViewHolder extends RecyclerView.ViewHolder {
            // Holder 与 item 的 View 一一对应
            public TextView todo;
            public Button completedBtn;
            public ItemViewHolder(@NonNull View view) {
                super(view);
                todo = view.findViewById(R.id.todoItemText);
                completedBtn = view.findViewById(R.id.todoItemBtn);
            }
        }


    }

    private class TodoItem {
        private String todo;
        private Boolean isCompleted;

        public TodoItem(String todo, Boolean isCompleted) {
            this.todo = todo;
            this.isCompleted = isCompleted;
        }

        public TodoItem(String todo) {
            this.todo = todo;
            this.isCompleted = false;
        }

        public String getTodo() {
            return todo;
        }

        public Boolean getCompleted() {
            return isCompleted;
        }

        public void setCompleted(Boolean completed) {
            isCompleted = completed;
        }
    }
}
