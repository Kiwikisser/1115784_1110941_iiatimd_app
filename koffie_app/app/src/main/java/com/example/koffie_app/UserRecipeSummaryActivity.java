package com.example.koffie_app;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserRecipeSummaryActivity extends AppCompatActivity {
    private TextView textViewTitle;
    private TextView textViewCoffeeBean;
    private TextView textViewIngredients;
    private TextView textViewCoffeeServings;
    private TextView textViewCoffeePrepTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_recipe_summary);

        Bundle recipeCardViewData = getIntent().getExtras();
        Log.d("id", recipeCardViewData.getString("recipe_id"));

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
    }
}
