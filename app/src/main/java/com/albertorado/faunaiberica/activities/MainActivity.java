package com.albertorado.faunaiberica.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.albertorado.faunaiberica.R;

public class MainActivity extends AppCompatActivity {


    RadioButton carnivoros;
    RadioButton carronieros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carnivoros = findViewById(R.id.carnivoros);
        carronieros = findViewById(R.id.carronieros);

        Intent irCarronieros = new Intent(this, Rapaces.class);
        Intent irCarnivoros = new Intent(this, Canivoros.class);

        carnivoros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(irCarnivoros);
            }
        });

        carronieros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(irCarronieros);
            }
        });



    }
}