package com.example.koffie_app;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

public class DeleteRecipeActivity implements Runnable{
    AppRoomDatabase db;
    String recipeId;
    public DeleteRecipeActivity(AppRoomDatabase db, String recipeId){
        this.db = db;
        this.recipeId = recipeId;
    }

    @Override
    public void run(){
        db.userRecipesDAO().deleteByUserId(this.recipeId);
    }
}