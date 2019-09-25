package com.example.hellowrold;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test);
        RecyclerView recyclerView = findViewById(R.id.testRecyclerView);
        List<String> mydata = new ArrayList<>();
        for (int i=1; i<=20;i++) {
            mydata.add(Integer.toString(i));
        }
        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        RecyclerView.Adapter adapter = new TestAdapter(mydata);
        recyclerView.setAdapter(adapter);

    }

    private class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {
        private List<String> datas;

        public TestAdapter(List<String> datas) {
            this.datas = datas;
        }

        @NonNull
        @Override
        public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView view = (TextView) LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            TestViewHolder holder = new TestViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
            holder.text.setText(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        private class TestViewHolder extends RecyclerView.ViewHolder {
            private TextView text;
            public TestViewHolder(@NonNull TextView textView) {
                super(textView);
                text = textView;
            }
        }
    }
}
