package com.example.koffie_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

public class AppLastIntroductionActivity extends AppCompatActivity implements View.OnClickListener, LoginDialogue.LoginDialogueListener{

    private UserAuthentication userAuthentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_introduction_final);

        userAuthentication = ViewModelProviders.of(this).get(UserAuthentication.class);

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
//        String username = UserAuthentication.getInstance(this).getUsername();
//        String password = UserAuthentication.getInstance(this).getPassword();

        Log.d("retrieveTexts: ", String.valueOf(uname + pword));

//        Log.d("retrieveLoginAttempt: ", String.valueOf(userAuthentication.findUserByNameAndPass(uname, pword)));
//        List<User> loggedUser = (List<User>) userAuthentication.findUserByNameAndPass(uname, pword);

//        Log.d("retrieveLoginAttempt: ", String.valueOf(userAuthentication.findUserByNameAndPass(uname, pword)));
        userAuthentication.findUserByNameAndPass(uname, pword);



        // retrieve user trying to log in.

//        if (username == uname && password == pword){
//            // TODO: RETURN USER TO HOME
//            Intent toMainScreen = new Intent(this, MainActivity.class);
//            startActivity(toMainScreen);
//        }
    }
}
