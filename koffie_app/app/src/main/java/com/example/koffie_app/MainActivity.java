package com.example.koffie_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

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

    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }
        return false;
    }
}