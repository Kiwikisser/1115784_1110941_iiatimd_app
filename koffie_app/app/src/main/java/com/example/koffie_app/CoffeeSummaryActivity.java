package com.example.koffie_app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CoffeeSummaryActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textViewTitle;
    private TextView textViewCoffeeBean;
    private TextView textViewIngredients;
    private TextView textViewDescription;
    private TextView textViewCoffeeServings;
    private TextView textViewCoffeePrepTime;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_coffeee_summary);
        Bundle coffeeCardViewData = getIntent().getExtras();
//        Bundle testBundle = getIntent().getBundleExtra("description");
//        db = AppRoomDatabase.getInstance(getApplicationContext());
//        Log.d("onCreate: ", coffeeCardViewData.getString("name"));

        Log.d("onCreate: ", String.valueOf(coffeeCardViewData.get("description")));

        textViewTitle = this.findViewById(R.id.coffee_summary_title);
        textViewCoffeeBean = this.findViewById(R.id.coffee_summary_coffeebean);
        textViewIngredients= this.findViewById(R.id.coffee_summary_description);
        textViewDescription= this.findViewById(R.id.coffee_summary_ingredients);
        textViewCoffeeServings = this.findViewById(R.id.coffee_summary_servings);
        textViewCoffeePrepTime = this.findViewById(R.id.coffee_summary_preptime);

        textViewTitle.setText(coffeeCardViewData.getString("name"));
        textViewCoffeeBean.setText(coffeeCardViewData.getString("coffee_bean"));
        textViewDescription.setText(String.valueOf(coffeeCardViewData.get("description")));
        textViewIngredients.setText(coffeeCardViewData.getString("ingredients"));
        textViewCoffeeServings.setText(String.valueOf(coffeeCardViewData.getInt("servings")));
        textViewCoffeePrepTime.setText(String.valueOf(coffeeCardViewData.getInt("prep_time")));
//        textViewCoffeeImage.setText(coffeeCardViewData.getString(""));

//        Button editRecipeButton = findViewById(R.id.button_coffee_return);
//        editRecipeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
