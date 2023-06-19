package com.example.swifterrand;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Sign_In extends AppCompatActivity {
     private EditText EmailAddress;
     private EditText Password;
    private Button login;
     private DatabaseHelper dbHelper;
    public void openHome(){

        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        dbHelper = new DatabaseHelper(getApplicationContext());

        EmailAddress = findViewById(R.id.EmailAddress);
        Password = findViewById(R.id.Password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EmailAddress.getText().toString();
                String password = Password.getText().toString();

                boolean loginSuccessful = dbHelper.checkUserCredentials(email, password);

                if (loginSuccessful) {
                    Toast.makeText(Sign_In.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    openHome();
                    // Perform appropriate actions (e.g., navigate to another activity)
                } else {
                    Toast.makeText(Sign_In.this, "Invalid email or password.", Toast.LENGTH_SHORT).show();
                }
            }



        });
    }
}