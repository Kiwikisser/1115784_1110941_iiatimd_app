package com.example.koffie_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class UserRecipeSummaryActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textViewTitle;
    private TextView textViewCoffeeBean;
    private TextView textViewIngredients;
    private TextView textViewCoffeeServings;
    private TextView textViewCoffeePrepTime;

    private UserRecipesViewModel userRecipesViewModel;
    AppRoomDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_recipe_summary);

        Bundle recipeCardViewData = getIntent().getExtras();
        Log.d("id", recipeCardViewData.getString("recipe_id"));
        db = AppRoomDatabase.getInstance(getApplicationContext());
        textViewTitle = this.findViewById(R.id.recipe_summary_title);
        textViewCoffeeBean = this.findViewById(R.id.recipe_summary_coffeebean);
        textViewIngredients= this.findViewById(R.id.recipe_summary_ingredients);
        textViewCoffeeServings = this.findViewById(R.id.recipe_summary_servings);
        textViewCoffeePrepTime = this.findViewById(R.id.recipe_summary_preptime);

        textViewTitle.setText(recipeCardViewData.getString("title"));
        textViewCoffeeBean.setText(recipeCardViewData.getString("coffee_bean"));
        textViewIngredients.setText(recipeCardViewData.getString("ingredients"));
        textViewCoffeeServings.setText(recipeCardViewData.getString("servings"));
        textViewCoffeePrepTime.setText(recipeCardViewData.getString("prep_time"));

        Button editRecipeButton = findViewById(R.id.button_edit_recipe);
        editRecipeButton.setOnClickListener(this);

        Button deleteRecipeButton = findViewById(R.id.button_delete_recipe);
        deleteRecipeButton.setOnClickListener(this);
    }
    public void onClick(View v){
        Bundle recipeCardViewData = getIntent().getExtras();
        switch(v.getId()) {
            case R.id.button_edit_recipe:
                Intent toIntroductionScreen = new Intent(this, RecipeCreateActivity.class); // evt redirect to edit activity
                startActivity(toIntroductionScreen);
                break;
            case R.id.button_delete_recipe:
                Log.d("deleteaction", "do it"); // evt redirect to edit activity
                Intent backToRecipesOverview = new Intent(this, UserRecipesOverviewActivity.class); // evt redirect to edit activity
                startActivity(backToRecipesOverview);
                userRecipesViewModel = ViewModelProviders.of(this).get(UserRecipesViewModel.class);
                new Thread(new DeleteRecipeActivity(db,recipeCardViewData.getString("recipe_id"))).start();
                break;
        }
    }
}
