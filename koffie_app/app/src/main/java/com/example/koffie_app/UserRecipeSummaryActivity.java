package com.example.koffie_app;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserRecipeSummaryActivity extends AppCompatActivity {
    private TextView textViewTitle;
    private TextView textViewIngredients;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_recipe_summary);

        Bundle recipeCardViewData = getIntent().getExtras();
        Log.d("title", recipeCardViewData.getString("title"));
        Log.d("title", recipeCardViewData.getString("ingredients"));
        textViewTitle = this.findViewById(R.id.recipe_summary_title);
        textViewIngredients= this.findViewById(R.id.recipe_summary_ingredients);
        textViewTitle.setText(recipeCardViewData.getString("title"));
        textViewIngredients.setText(recipeCardViewData.getString("ingredients"));
    }
}
