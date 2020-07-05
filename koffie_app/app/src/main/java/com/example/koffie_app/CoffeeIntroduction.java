package com.example.koffie_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CoffeeIntroduction extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_introduction);
        Button startIntroductionButton = findViewById(R.id.button_nextIntroduction);
        startIntroductionButton.setOnClickListener(this);
    }
    public void onClick(View v){
        Intent toLastIntroductionScreen = new Intent(this, CoffeeIntroductionLast.class);
        startActivity(toLastIntroductionScreen);
    }
}

