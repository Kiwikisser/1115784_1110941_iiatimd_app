package com.example.koffie_app;

import android.util.Log;

public class InsertNewUserRecipe implements Runnable{
    AppRoomDatabase db;
    UserRecipes user_recipe;
    public InsertNewUserRecipe(AppRoomDatabase db, UserRecipes user_recipe){
        this.db = db;
        this.user_recipe = user_recipe;
    }

    @Override
    public void run(){
        db.userRecipesDAO().insert(this.user_recipe);
    }
}
