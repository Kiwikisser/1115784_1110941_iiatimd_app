package com.example.koffie_app;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CoffeeOverviewActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_coffee_overview);

        recyclerView = findViewById(R.id.recyclerView_coffee_overview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();            //performance thing

        String dummyString = "This coffee very delicious, make dis every day yes.";
        Coffee[] coffees = new Coffee[20];
        for (int i = 0; i < 20; i++){
            coffees[i] = new Coffee("Coffee " + i, dummyString, "#ffffff");
        }

        recyclerViewAdapter = new CoffeeOverviewAdapter(coffees, this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
