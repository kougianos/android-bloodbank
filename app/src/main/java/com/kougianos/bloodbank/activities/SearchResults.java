package com.kougianos.bloodbank.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kougianos.bloodbank.R;
import com.kougianos.bloodbank.adapters.SearchAdapter;
import com.kougianos.bloodbank.models.Donor;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchResults extends AppCompatActivity {

    private RecyclerView recyclerView;
    List<Donor> donorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        donorList = new ArrayList<>();
        String json;
        String city;
        String bloodGroup;
        Intent intent = getIntent();
        json = intent.getStringExtra("json");
        city = intent.getStringExtra("city");
        bloodGroup = intent.getStringExtra("blood_group");

        TextView heading = findViewById(R.id.heading);
        String str = "Donors in "+city+" with blood group "+bloodGroup;
        heading.setText(str);

        Gson gson = new Gson();
        // Create type of JSON to bind it to gson
        Type type = new TypeToken<List<Donor>>() {
        }.getType();
        List<Donor> dataModels = gson.fromJson(json, type);

        if(dataModels != null && dataModels.isEmpty()) {
            heading.setText("No results");
        } else if(dataModels != null) {
            donorList.addAll(dataModels);
        }

        // Create recycler view and populate it
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        SearchAdapter searchAdapter = new SearchAdapter(donorList, getApplicationContext());
        recyclerView.setAdapter(searchAdapter);

    }
}
