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

import java.util.List;

public class UserRecipesOverviewActivity extends AppCompatActivity implements View.OnClickListener {
        private UserRecipesViewModel userRecipesViewModel;

        @Override
        public void onBackPressed(){
            Intent backToHomePage = new Intent(this, AppHomePageActivity.class); // evt redirect to edit activity
            startActivity(backToHomePage);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
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
        overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
    }
}
