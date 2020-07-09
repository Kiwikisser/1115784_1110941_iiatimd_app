package com.example.koffie_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AppHomePageActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_home_page);

        Button toLearnAboutCoffeeActivity = findViewById(R.id.button_home_learncoffee);
        Button toCoffeeOverviewActivity = findViewById(R.id.button_home_choosecoffee);
        Button toUserRecipesActivity = findViewById(R.id.button_home_userrecipes);
        toLearnAboutCoffeeActivity.setOnClickListener(this);
        toCoffeeOverviewActivity.setOnClickListener(this);
        toUserRecipesActivity.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.button_home_learncoffee:
                Intent toLearnAboutCoffeeScreen = new Intent(this, RegisterActivity.class); //evt learn about coffee pages
                startActivity(toLearnAboutCoffeeScreen);
                break;
            case R.id.button_home_choosecoffee:
                Intent toCoffeeScreen = new Intent(this, CoffeeOverviewActivity.class);
                startActivity(toCoffeeScreen);
                break;
            case R.id.button_home_userrecipes:
                Intent toUserRecipesScreen = new Intent(this, UserRecipesOverviewActivity.class);
                startActivity(toUserRecipesScreen);
                break;
        }
    }
}
