package com.example.koffie_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CoffeeIntroductionLast extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_introduction_final);

        Button createAccIntroductionButton = findViewById(R.id.button_intro_createAccount);
        Button loginIntroductionButton = findViewById(R.id.button_intro_login);
        Button skipToHPIntroductionButton = findViewById(R.id.button_intro_skip);

        createAccIntroductionButton.setOnClickListener(this);
        loginIntroductionButton.setOnClickListener(this);
        skipToHPIntroductionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.button_intro_createAccount:
                Intent toRegisterScreen = new Intent(this, Register.class);
                startActivity(toRegisterScreen);
                break;
            case R.id.button_intro_login:
                Intent toLoginScreen = new Intent(this, Login.class);
                startActivity(toLoginScreen);
                break;
            case R.id.button_intro_skip:
                Intent toHomeScreen = new Intent(this, MainActivity.class); //evt to homepage activity
                startActivity(toHomeScreen);
                break;
        }
    }
}
