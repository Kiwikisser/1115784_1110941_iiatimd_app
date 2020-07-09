package com.example.koffie_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AppLastIntroductionActivity extends AppCompatActivity implements View.OnClickListener, LoginDialogue.LoginDialogueListener{
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
                Intent toRegisterScreen = new Intent(this, RegisterActivity.class);
                startActivity(toRegisterScreen);
                break;
            case R.id.button_intro_login:
//                Intent toLoginScreen = new Intent(this, LoginActivity.class);
//                startActivity(toLoginScreen);
                LoginDialogue exampleDialogue = new LoginDialogue();
                exampleDialogue.show(getSupportFragmentManager(), "login dialog");
                break;
            case R.id.button_intro_skip:
                Intent toHomeScreen = new Intent(this, CoffeeOverviewActivity.class); //evt to homepage activity
                startActivity(toHomeScreen);
                break;
        }
    }

    @Override
    public void retrieveTexts(String uname, String pword) {
        // call login instance
        String username = UserAuthentication.getInstance(this).getUsername();
        String password = UserAuthentication.getInstance(this).getPassword();

        if (username == uname && password == pword){
            // TODO: RETURN USER TO HOME
            Intent toMainScreen = new Intent(this, MainActivity.class);
            startActivity(toMainScreen);
        }
    }
}
