package com.example.koffie_app;

import android.util.Log;

public class GetCoffeeTask implements Runnable {

    AppRoomDatabase db;

    public GetCoffeeTask(AppRoomDatabase db){
        this.db = db;
    }

    @Override
    public void run() {
        String name = db.coffeeDAO().getAll().get(0).getName();
        Log.d("run: ", name);
    }

}
