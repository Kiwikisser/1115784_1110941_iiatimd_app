package com.example.koffie_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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

import java.util.List;

public class UserRecipesOverviewActivity extends AppCompatActivity implements View.OnClickListener {
//      AppRoomDatabase database;
//      String GETURL = "http://192.168.178.115:8000/api/recipes/mauriccio";
        private UserRecipesViewModel userRecipesViewModel;
        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.l_user_recipe_recyclerview);
            // get code to display cards, should get data from local database instead
            RecyclerView recyclerView = findViewById(R.id.user_recipes_recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);

            Button createRecipeRedirectButton = findViewById(R.id.button_recipe_overview_add);
            createRecipeRedirectButton.setOnClickListener(this);
            final UserRecipesAdapter adapter = new UserRecipesAdapter();  //here
            recyclerView.setAdapter(adapter);

            userRecipesViewModel = ViewModelProviders.of(this).get(UserRecipesViewModel.class);
            userRecipesViewModel.getAllRecipes().observe(this, new Observer<List<UserRecipes>>() {
                @Override
                public void onChanged(List<UserRecipes> userRecipes) {
                    adapter.setUserRecipe(userRecipes);
                }
            });
        }
    public void onClick(View v){
        Intent toIntroductionScreen = new Intent(this, RecipeCreateActivity.class);
        startActivity(toIntroductionScreen);
    }
}
