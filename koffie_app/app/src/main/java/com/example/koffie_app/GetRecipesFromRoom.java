package com.example.koffie_app;

import android.util.Log;

public class GetRecipesFromRoom implements Runnable {
    AppRoomDatabase db;

    public GetRecipesFromRoom(AppRoomDatabase db){
        this.db = db;
    }
    @Override
    public void run(){
        String name = db.userRecipesDAO().getAll().get(0).getRecipe_name();
        Log.d("getfirstname", name);
    }
}
