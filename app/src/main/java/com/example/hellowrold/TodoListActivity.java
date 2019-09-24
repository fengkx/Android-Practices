package com.example.hellowrold;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TodoListActivity extends AppCompatActivity {

    private EditText inputTodo;
    private List<TodoItem> todoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        inputTodo = findViewById(R.id.todoInput);
        Button addTodoBtn = findViewById(R.id.addTodoBtn);
        addTodoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String todo = inputTodo.getText().toString();
                todoList.add(new TodoItem(todo));
                updateList();
                inputTodo.setText("");
            }
        });
    }

    private void updateList() {
        TodoItemAdapter adapter = new TodoItemAdapter(TodoListActivity.this, R.layout.todo_item, todoList);
        ListView listView = findViewById(R.id.todoList);
        listView.setAdapter(adapter);
    }

    public class TodoItemAdapter extends ArrayAdapter<TodoItem> {
        private int resourceId;
        public TodoItemAdapter(@NonNull Context context, int resource, @NonNull List<TodoItem>datas) {
            super(context, resource, datas);
            resourceId = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final TodoItem item = getItem(position);
            View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            // false 表示只让我们在父布局中声明的layout属性生效，但不会为这个View 添加父布局
            TextView todoText = view.findViewById(R.id.todoItemText);
            Button completeBtn = view.findViewById(R.id.todoItemBtn);
            completeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    todoList.remove(item);
                    updateList();
                }
            });

            todoText.setText(item.getTodo());

            return view;
        }
    }

    private class TodoItem {
        String todo;
        Boolean isCompleted;

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
