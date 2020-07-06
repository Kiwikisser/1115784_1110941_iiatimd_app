package com.example.koffie_app;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

class Coffee {
    private String name;
    private String description;
    private String colorCode;
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

    //    public String getImage() {
//        return image;
//    }
}
