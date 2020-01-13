package com.example.microwave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {
    private ImageView meat;
    private ImageView pie;
    private ImageView popcorn;
    private ImageView soup;
    private String meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        meat=(ImageView) findViewById(R.id.meat);

        meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meal="meat";
                moveToActivity2();
            }
        });

        pie=(ImageView) findViewById(R.id.pie);

        pie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meal="pie";
                moveToActivity2();
            }
        });

        popcorn=(ImageView) findViewById(R.id.popcorn);

        popcorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meal="popcorn";
                moveToActivity2();
            }
        });

        soup=(ImageView) findViewById(R.id.soup);

        soup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meal="soup";
                moveToActivity2();
            }
        });



    }

    private void moveToActivity2() {
        Intent intent = new Intent(HomeActivity.this, functionsActivity.class);
        intent.putExtra("MEAL", meal);
        startActivity(intent);
    }


}
