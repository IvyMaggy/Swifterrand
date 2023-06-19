package com.example.swifterrand;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Sign_up extends AppCompatActivity {
    private  EditText Id_Number;
    private EditText User_Name;
     private EditText Email_Address;
     private EditText User_password;
     private EditText User_phone;
     private EditText City;
     private DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        dbHelper = new DatabaseHelper(getApplicationContext());

        Id_Number = findViewById(R.id.Id_Number);
        User_Name = findViewById(R.id.User_Name);
        Email_Address = findViewById(R.id.Email_Address);
        User_password = findViewById(R.id.User_Password);
        User_phone = findViewById(R.id.User_Phone);
        City= findViewById(R.id.City);
        Button submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id_No = Id_Number.getText().toString();
                String user_name = User_Name.getText().toString();
                String email = Email_Address.getText().toString();
                String password = User_password.getText().toString();
                String phone = User_phone.getText().toString();
                String city= City.getText().toString();

                Log.d("DatabaseHelper", "Inserting user: id_No = " + id_No + ", user_name = " + user_name + ", email = " + email + ", password = " + password + ", phone = " + phone + ", city = " + city);

                long newRowId = dbHelper.insertUser(id_No,user_name,email,password,phone,city);

                if (newRowId != -1) {
                    openHome();
                    Toast.makeText(Sign_up.this, "User registered successfully!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(Sign_up.this, "Error registering user.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void openHome(){

        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}
