package com.muzzdev.filedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    Button btnSave, btnFetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.etName);
        btnSave = findViewById(R.id.btnSave);
        btnFetch = findViewById(R.id.btnFetch);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileOutputStream fileOutputStream;
                try {

                    fileOutputStream = openFileOutput("mydata", MODE_PRIVATE);
                    fileOutputStream.write(etName.getText().toString().getBytes());
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileInputStream fileInputStream;
                StringBuilder stringBuilder = new StringBuilder();

                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput("mydata")));
                    String str;

                    while ((str = bufferedReader.readLine()) != null) {
                        stringBuilder.append(str);
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                etName.setText(stringBuilder);

            }
        });
    }
}