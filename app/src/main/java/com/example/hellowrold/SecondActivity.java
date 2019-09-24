package com.example.hellowrold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button testListBtn = findViewById(R.id.listTestBtn);
        testListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ListTestActivity.class);
                startActivity(intent);
            }
        });
        Button todoListBtn = findViewById(R.id.todoListBtn);
        todoListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, TodoListActivity.class);
                startActivity(intent);
            }
        });
    }
}
