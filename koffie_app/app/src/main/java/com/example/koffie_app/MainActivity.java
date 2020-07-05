package com.example.koffie_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_introduction);

        // used next button to test my activity, redirect later
        Button toOverviewButton = findViewById(R.id.button_nextIntroduction);
        toOverviewButton.setOnClickListener(this);
    }

    public void onClick(View v){
        Intent toSecondScreenIntent = new Intent(this, CoffeeOverviewActivity.class);
        startActivity(toSecondScreenIntent);
    }
}