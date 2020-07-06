package com.example.koffie_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.os.Handler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_main_page);
        Button startIntroductionButton = findViewById(R.id.button_startIntroduction);
        startIntroductionButton.setOnClickListener(this);

        Handler handler = new Handler();
        int connectionIntverval = 20000;
        AppRoomDatabase db = AppRoomDatabase.getInstance(this.getApplicationContext());
        handler.post(ConnectionPeriodicTask.getInstance(this, handler, connectionIntverval, db));
    }

    public void onClick(View v){
        Intent toIntroductionScreen = new Intent(this, CoffeeIntroduction.class);
        startActivity(toIntroductionScreen);
    }

//    public void retrieveData(){
//        RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
//
//    }

//    public boolean isOnline() {
//        Runtime runtime = Runtime.getRuntime();
//        try {
//            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
//            int     exitValue = ipProcess.waitFor();
//            return (exitValue == 0);
//        } catch (IOException e)        { e.printStackTrace(); }
//        catch (InterruptedException e) { e.printStackTrace(); }
//        return false;
//    }
}