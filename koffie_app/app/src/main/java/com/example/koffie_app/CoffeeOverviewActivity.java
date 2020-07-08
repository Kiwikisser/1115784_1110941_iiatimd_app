package com.example.koffie_app;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CoffeeOverviewActivity extends AppCompatActivity {

//    public RecyclerView recyclerView;
//    private RecyclerView.Adapter recyclerViewAdapter;
//    private RecyclerView.LayoutManager layoutManager;
//    private CoffeeDAO coffeeDAO;
//    private AppRoomDatabase database;

    private CoffeeViewModel coffeeViewModel;

    @Override
    protected void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_coffee_overview);

        RecyclerView recyclerView = findViewById(R.id.recyclerView_coffee_overview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final CoffeeAdapter adapter = new CoffeeAdapter();
        recyclerView.setAdapter(adapter);

        coffeeViewModel = ViewModelProviders.of(this).get(CoffeeViewModel.class);
        coffeeViewModel.getAllCoffee().observe(this, new Observer<List<Coffee>>() {
            @Override
            public void onChanged(List<Coffee> coffees) {
                adapter.setCoffee(coffees);
            }
        });


//        recyclerView = findViewById(R.id.recyclerView_coffee_overview);
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.hasFixedSize();            //performance thing
//
////        retrieve objects from dao
////        pass into overviewadapter obj
////        String dummyString = "This coffee very delicious, make dis every day yes.";
////        Coffee[] coffees = new Coffee[20];
////        for (int i = 0; i < 20; i++){
////            coffees[i] = new Coffee(i, "Coffee " + i, dummyString);
////        }
//
//        database = AppRoomDatabase.getInstance(this.getApplicationContext());
////        GetCoffeeTask getCoffeeTask = new GetCoffeeTask(db);
////        new Thread(getCoffeeTask).start();
////        Coffee[] coffee = db.coffeeDAO().getAll();
//
//
//        // what are callables, honestly
////        ExecutorService executor = Executors.newSingleThreadExecutor();
////        Future<ArrayList<Coffee>> result = executor.submit();
//
////        recyclerViewAdapter = new CoffeeOverviewAdapter(coffee, this);
//        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
