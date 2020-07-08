package com.example.koffie_app;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserRecipes{
    /*private JSONArray recipe_id;*/
    @ColumnInfo
    private String username;
    @ColumnInfo
    private String recipe_name;

    @PrimaryKey
    private int uuid;
   /* private String recipe_ingredients;
    private String coffee_bean;
    private Integer coffee_servings;
    private Integer coffee_prep_time;*/

    public UserRecipes(String username, String recipe_name, int uuid){
        this.username = username;
        this.recipe_name = recipe_name;
        this.uuid = uuid;
    }

    public String getUsername(){
        return this.username;
    }

    public String getRecipe_name(){
        return this.recipe_name;
    }
    public int getUuid(){
        return this.uuid;
    }
}
