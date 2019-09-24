package com.example.hellowrold;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListTestActivity extends AppCompatActivity {
    private ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_test);
        for (int i=0;i<233;i++) {
            data.add(Integer.toString(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                ListTestActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listView = findViewById(R.id.todoList);
        listView.setAdapter(adapter);
    }
}
