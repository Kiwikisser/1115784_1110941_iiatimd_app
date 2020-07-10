package com.example.koffie_app;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

public class CoffeeInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_coffee_information);
        TextView textViewTitle = findViewById(R.id.coffeeintro_textView);
        textViewTitle.setTypeface(ResourcesCompat.getFont(this, R.font.rock_salt_regular));
    }
}
