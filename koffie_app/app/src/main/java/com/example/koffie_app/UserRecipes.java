package com.example.koffie_app;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_recipes")
public class UserRecipes{
    @NonNull
    @PrimaryKey
    private String recipeId;

    @ColumnInfo
    private String username;

    @ColumnInfo
    private String recipeName;

    @ColumnInfo
    private String recipeIngredients;

    @ColumnInfo
    private String coffeeBean;

    @ColumnInfo
    private int coffeeServings;

    @ColumnInfo
    private int coffeePrepTime;


    public UserRecipes(String recipeId, String username, String recipeName, String recipeIngredients, String coffeeBean, int coffeeServings, int coffeePrepTime){
        this.recipeId = recipeId;
        this.username = username;
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.coffeeBean = coffeeBean;
        this.coffeeServings = coffeeServings;
        this.coffeePrepTime = coffeePrepTime;
    }

    public String getRecipeId(){
        return recipeId;
    }

    public String getUsername(){
        return username;
    }

    public String getRecipeName(){
        return recipeName;
    }

    public String getRecipeIngredients(){
        return recipeIngredients;
    }

    public String getCoffeeBean(){
        return coffeeBean;
    }

    public int getCoffeeServings(){
        return coffeeServings;
    }

    public int getCoffeePrepTime(){
        return coffeePrepTime;
    }
}
