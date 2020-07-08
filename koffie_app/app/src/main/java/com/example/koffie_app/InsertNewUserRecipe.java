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
        db.userRecipesDAO().InsertRecipe(this.user_recipe);
        String recipe_id = db.userRecipesDAO().getAll().get(0).getRecipeId();
        String username = db.userRecipesDAO().getAll().get(0).getUsername();
        String recipe_name = db.userRecipesDAO().getAll().get(0).getRecipeName();
        String recipe_ingredients = db.userRecipesDAO().getAll().get(0).getRecipeIngredients();
        String coffee_bean = db.userRecipesDAO().getAll().get(0).getCoffeeBean();
        String coffee_servings = String.valueOf(db.userRecipesDAO().getAll().get(0).getCoffeeServings());
        String coffee_prep_time = String.valueOf(db.userRecipesDAO().getAll().get(0).getCoffeePrepTime());
        Log.d("id is: ", recipe_id);
        Log.d("username is: ", username);
        Log.d("recipe name is: ", recipe_name);
        Log.d("recipe ingredients: ", recipe_ingredients);
        Log.d("coffee bean is: ", coffee_bean);
        Log.d("amount of servings: ", coffee_servings);
        Log.d("preptime in minutes: ", coffee_prep_time);
    }
}
