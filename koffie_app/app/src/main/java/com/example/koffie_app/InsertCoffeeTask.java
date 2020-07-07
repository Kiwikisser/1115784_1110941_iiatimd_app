package com.example.koffie_app;

import android.util.Log;

public class InsertCoffeeTask implements Runnable{

    AppRoomDatabase database;
    final Coffee[] coffee;

    public InsertCoffeeTask(AppRoomDatabase database, Coffee[] coffee){
        this.database = database;
        this.coffee = coffee;
    }

    @Override
    public void run() {
        for (int i = 0; i < coffee.length; i++){
            if (coffee[i] != null){
                database.coffeeDAO().InsertCoffee(coffee[i]);
            }
        }

//        int size = database.coffeeDAO().getAll().length();
//        String name;
//        for (int i = 0; i < size; i++){
//            name = database.coffeeDAO().getAll()[i].getName();
//            Log.d( "name: ", name);
//        }
    }
}
