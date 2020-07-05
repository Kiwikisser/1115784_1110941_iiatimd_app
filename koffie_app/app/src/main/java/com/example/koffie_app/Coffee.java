package com.example.koffie_app;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

class Coffee {
    private String name;
    private String description;
//    private String image = "dezeinfovoorbedankt.jpg";
//    int resID;

    public Coffee(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

//    public String getImage() {
//        return image;
//    }
}
