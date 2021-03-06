package com.example.sqlite;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import java.util.ArrayList;

public class WallActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    RecyclerView newsRecycler;
    ArrayList<NewsModel> list = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall);

        databaseHelper = new DatabaseHelper(this);

        newsRecycler = findViewById(R.id.recycler_news);
        newsRecycler.setHasFixedSize(true);



        layoutManager = new LinearLayoutManager(this);
        newsRecycler.setLayoutManager(layoutManager);


        Cursor res = databaseHelper.getDataNews();
        if(res.getCount() == 0){
            Toast.makeText(this,"Нет данных", Toast.LENGTH_SHORT).show();
            return;
        }

        while (res.moveToNext()){
            String name = res.getString(0);
            String description = res.getString(1);
            list.add(new NewsModel(name,description));
            //  Log.i("12312311", "onCreate: 123123");
        }

        newsRecycler.setAdapter(new NewsAdapter(getApplicationContext(), list));
    }
}