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
        //db.userRecipesDAO().InsertRecipe(this.user_recipe);
        String name = db.userRecipesDAO().getAll().get(1).getUsername();
        String ingredient = db.userRecipesDAO().getAll().get(1).getRecipe_name();
        Log.d("name is = ", name);
        Log.d("ingredients are = ", ingredient);
    }
}
