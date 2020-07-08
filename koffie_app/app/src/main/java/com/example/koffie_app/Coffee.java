package com.example.koffie_app;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "coffee_table")
public class Coffee {

    @PrimaryKey
    private int id;

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String description;

//    @ColumnInfo
//    private String colorCode;

    @ColumnInfo
    private String beans;

    @ColumnInfo
    private int volume;

    @ColumnInfo
    private String roast;

    @ColumnInfo
    private int prepTime;



    public Coffee(int id, String name, String description, String beans, int volume, String roast, int prepTime){
        this.id = id;
        this.name = name;
        this.description = description;
//        this.colorCode = colorCode;
        this.beans = beans;
        this.volume = volume;
        this.roast = roast;
        this.prepTime = prepTime;
    }

    @Ignore
    public Coffee(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

//    public String getColorCode() {
//        return colorCode;
//    }

    public int getVolume() {
        return volume;
    }

    public String getBeans() {
        return beans;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public String getRoast() {
        return roast;
    }

    //    public String getImage() {
//        return image;
//    }
}
