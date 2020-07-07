package com.example.koffie_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class UserRecipesOverviewActivity extends AppCompatActivity {
    private RecyclerView recipeRecyclerView;
    private RecyclerView.Adapter recipeRecyclerViewAdapter;
    private RecyclerView.LayoutManager recipeLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_user_recipe_recyclerview);

        recipeRecyclerView = findViewById(R.id.user_recipes_recyclerView);
        recipeLayoutManager = new LinearLayoutManager(this);
        recipeRecyclerView.setLayoutManager(recipeLayoutManager);
        recipeRecyclerView.hasFixedSize();  //evt verwijderen door n hoeveelheid items, kan performance kosten

        // Dummy data
        String[] recipeTitles = new String[200];
        for(int i =0;i< 200;i++){
            recipeTitles[i] = "Coffee" + i;
        }
        recipeRecyclerViewAdapter = new UserRecipesAdapter(recipeTitles);
        recipeRecyclerView.setAdapter(recipeRecyclerViewAdapter);
    }
}
