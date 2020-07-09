package com.example.koffie_app;

import android.util.Log;

public class DeleteRecipeActivity implements Runnable{
    AppRoomDatabase db;
    String recipeId;
    public DeleteRecipeActivity(AppRoomDatabase db, String recipeId){
        this.db = db;
        this.recipeId = recipeId;
    }

    @Override
    public void run(){
        db.userRecipesDAO().deleteByUserId(this.recipeId);
    }
}