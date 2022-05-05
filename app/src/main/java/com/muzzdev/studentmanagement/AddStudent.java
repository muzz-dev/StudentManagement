package com.muzzdev.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddStudent extends AppCompatActivity {
    EditText etName, etPhone, etGender, etAddress;
    Button btnAddStudent;

    StudentDB studentDB = new StudentDB(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        getSupportActionBar().setTitle("Add Student");
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etGender = findViewById(R.id.etGender);
        etAddress = findViewById(R.id.etAddress);
        btnAddStudent = findViewById(R.id.btnAddStudent);

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentDB.addStudent(etName.getText().toString(), etAddress.getText().toString(), etGender.getText().toString(), etPhone.getText().toString());
                Intent intent = new Intent(AddStudent.this,StudentList.class);
                startActivity(intent);
            }
        });
    }
}