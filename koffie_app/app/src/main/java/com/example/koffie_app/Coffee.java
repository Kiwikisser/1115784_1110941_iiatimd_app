package com.example.koffie_app;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "coffee_table")
public class Coffee {
    @NonNull
    @PrimaryKey
    private int id;

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String ingredients;

    @ColumnInfo
    private String description;

//    @ColumnInfo
//    private String colorCode;

    @ColumnInfo
    private String beans;

    @ColumnInfo
    private int servings;

    @ColumnInfo
    private int prepTime;

    @ColumnInfo
    private String image;

    public Coffee(int id,
                  String name,
                  String ingredients,
                  String description,
                  String beans,
                  int servings,
                  int prepTime,
                  String image){
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
//        this.colorCode = colorCode;
        this.beans = beans;
        this.servings = servings;
        this.prepTime = prepTime;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getServings() {
        return servings;
    }

    public String getDescription() {
        return description;
    }

//    public String getColorCode() {
//        return colorCode;
//    }

    public String getBeans() {
        return beans;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public String getImage() {
        return image;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBeans(String beans) {
        this.beans = beans;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
