package com.example.koffie_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AppRoomDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_main_page);
        Button startIntroductionButton = findViewById(R.id.button_startIntroduction);
        startIntroductionButton.setOnClickListener(this);

//        db = AppRoomDatabase.getInstance(getApplicationContext());
//        db.coffeeDAO()
    }

    public void onClick(View v){
        Intent toIntroductionScreen = new Intent(this, CoffeeIntroduction.class);
        startActivity(toIntroductionScreen);
    }
}