package com.example.swifterrand;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button client_button = findViewById(R.id.client_button);
        Button swifter_button = findViewById(R.id.swifter_button);

        client_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle client button click
                Intent intent = new Intent(Home.this, Client.class);
                startActivity(intent);
            }
        });

        swifter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle customer button click
                Intent intent = new Intent(Home.this, Swifter.class);
                startActivity(intent);
            }
        });
    }
}
