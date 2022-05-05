package com.muzzdev.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;

    StudentDB studentDB = new StudentDB(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("LOGIN");

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        SharedPreferences prefs = getSharedPreferences("Login", MODE_PRIVATE);

        if(prefs.getBoolean("islogin",true)){
            Toast.makeText(MainActivity.this,"Already Login",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, StudentList.class);
            startActivity(intent);
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean checkLogin = studentDB.Login(etUsername.getText().toString(), etPassword.getText().toString());
                if (checkLogin) {
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("islogin",true);
                    editor.apply();

                    Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, StudentList.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Login Fail", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}