package com.example.koffie_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.os.Handler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AppRoomDatabase database;
    private CoffeeViewModel coffeeViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_main_page);
        Button startIntroductionButton = findViewById(R.id.button_startIntroduction);
        startIntroductionButton.setOnClickListener(this);

        coffeeViewModel = ViewModelProviders.of(this).get(CoffeeViewModel.class);
        Handler handler = new Handler();
        int connectionIntverval = 20000;
        handler.post(ConnectionPeriodicTask.getInstance(this, handler, connectionIntverval, coffeeViewModel));
    }

    public void onClick(View v){
        Intent toHomePage = new Intent(this, AppHomePageActivity.class);
        startActivity(toHomePage);
    }
}