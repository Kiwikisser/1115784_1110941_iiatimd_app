package com.example.koffie_app;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
class Coffee {

    @ColumnInfo
    private String name;
    @ColumnInfo
    private String description;
    @ColumnInfo
    private String colorCode;
    @PrimaryKey
    private int id;
//    private String image = "dezeinfovoorbedankt.jpg";
//    int resID;

    public Coffee(String name, String description, String colorCode){
        this.name = name;
        this.description = description;
        this.colorCode = colorCode;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getColorCode() {
        return colorCode;
    }

    public int getId() {
        return id;
    }

    //    public String getImage() {
//        return image;
//    }
}
