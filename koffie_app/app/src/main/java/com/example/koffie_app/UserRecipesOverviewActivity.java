package com.example.koffie_app;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserRecipesOverviewActivity extends AppCompatActivity {
    private RecyclerView recipeRecyclerView;
    private RecyclerView.Adapter recipeRecyclerViewAdapter;
    private RecyclerView.LayoutManager recipeLayoutManager;

    String recipeTitles[];
    String GETURL = "http://192.168.56.1:8000/api/recipes/mipanamiguel";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_user_recipe_recyclerview);
        RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();

        // get code to display cards, should get data from local database instead

        /*recipeRecyclerView = findViewById(R.id.user_recipes_recyclerView);
        recipeLayoutManager = new LinearLayoutManager(this);
        recipeRecyclerView.setLayoutManager(recipeLayoutManager);
        //recipeRecyclerView.hasFixedSize();  //evt verwijderen door n hoeveelheid items, kan performance kosten

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, GETURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //evt post to room db
                recipeTitles = new String[response.length()];
                for(int i =0; i< response.length();i++){
                    try {
                        recipeTitles[i] = response.getJSONObject(i).getString("recipe_name");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("recipes", recipeTitles.toString());
                recipeRecyclerViewAdapter = new UserRecipesAdapter(recipeTitles);
                recipeRecyclerView.setAdapter(recipeRecyclerViewAdapter);
                recipeRecyclerViewAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.getMessage());
            }
        });
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);*/
    }
}
