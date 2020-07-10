package com.example.koffie_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AppRoomDatabase database;
    private CoffeeViewModel coffeeViewModel;
    SharedPreferences prefs = null;
    Intent logButtonIntent;
    private TextView textViewMainHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.l_main_page_stime);
        textViewMainHeader = this.findViewById(R.id.mainpage_header);

        if(false){ // User is not logged in
            Button redirectToLoginButton = findViewById(R.id.button_mainpage_log);
            redirectToLoginButton.setOnClickListener(this);
            // set intent to redirect to login page
            textViewMainHeader.setText("Is not logged in");
            redirectToLoginButton.setText("login");
            redirectToLoginButton.setBackgroundColor(Color.parseColor("#526C46"));
            logButtonIntent = new Intent(this, LoginActivity.class);
        }
        else{ // User is logged in
            Button startLogoutButton = findViewById(R.id.button_mainpage_log);
            startLogoutButton.setOnClickListener(this);
            textViewMainHeader.setText("Welcome " + "dddd"); // second value is username

            //do something for logout
            logButtonIntent = new Intent(this, MainActivity.class);
        }
        Button redirectToHomePage = findViewById(R.id.button_startHome);
        redirectToHomePage.setOnClickListener(this);

        Button redirectToIntroduction = findViewById(R.id.button_mainpage_introduction);
        redirectToIntroduction.setOnClickListener(this);

        coffeeViewModel = ViewModelProviders.of(this).get(CoffeeViewModel.class);
        Handler handler = new Handler();
        int connectionIntverval = 20000;
        handler.post(ConnectionPeriodicTask.getInstance(this, handler, connectionIntverval, coffeeViewModel));

        prefs = getSharedPreferences("com.example.koffie_app", MODE_PRIVATE);
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (prefs.getBoolean("firstrun", true)) {
            setContentView(R.layout.l_main_page);
            Button startIntroductionButton = findViewById(R.id.button_startIntroduction);
            startIntroductionButton.setOnClickListener(this);

            coffeeViewModel = ViewModelProviders.of(this).get(CoffeeViewModel.class);
            Handler handler = new Handler();
            int connectionIntverval = 20000;
            handler.post(ConnectionPeriodicTask.getInstance(this, handler, connectionIntverval, coffeeViewModel));

            prefs.edit().putBoolean("firstrun", false).commit();
        }
        // TODO: remove this test account
        UserAuthentication.getInstance(this).setUsername("a");
        UserAuthentication.getInstance(this).setPassword("2222");
//        UserAuthentication.getInstance(this).isAuthenticated();

    }
    public void onClick(View v){
//        Intent toHomePage = new Intent(this, AppHomePageActivity.class);
//        startActivity(toHomePage);
        switch(v.getId()){
            case R.id.button_startIntroduction:
                Intent toIntroductionScreen = new Intent(this, AppIntroductionActivity.class);
                startActivity(toIntroductionScreen);
                break;
            case R.id.button_startHome:
                Intent toHomePage = new Intent(this, AppHomePageActivity.class);
                startActivity(toHomePage);
                break;
            case R.id.button_mainpage_log:
                startActivity(logButtonIntent);
                break;
            case R.id.button_mainpage_introduction:
                Intent toIntroductionAgain = new Intent(this, AppIntroductionActivity.class);
                startActivity(toIntroductionAgain);
                break;
        }
    }
}