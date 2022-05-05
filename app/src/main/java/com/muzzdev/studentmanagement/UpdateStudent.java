package com.muzzdev.studentmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateStudent extends AppCompatActivity {
    EditText txtname, txtaddress, txtgender, txtphone;
    Button btnUpdate, btnDelete;

    StudentDB studentDB = new StudentDB(this, null, null, 1);

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        txtname = findViewById(R.id.txtname);
        txtaddress = findViewById(R.id.txtaddress);
        txtgender = findViewById(R.id.txtgender);
        txtphone = findViewById(R.id.txtphone);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        String studentId = getIntent().getExtras().getSerializable("studentId").toString();
        Toast.makeText(UpdateStudent.this, "Intent Data: " + studentId, Toast.LENGTH_LONG).show();
        Cursor cursor = studentDB.getStudentById(studentId);
        while (cursor.moveToNext()) {
            txtname.setText(cursor.getString(cursor.getColumnIndex("name")).toString());
            txtaddress.setText(cursor.getString(cursor.getColumnIndex("address")).toString());
            txtgender.setText(cursor.getString(cursor.getColumnIndex("gender")).toString());
            txtphone.setText(cursor.getString(cursor.getColumnIndex("phone")).toString());
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentDB.updateStudent(txtname.getText().toString(), txtaddress.getText().toString(), txtgender.getText().toString(), txtphone.getText().toString(),studentId);
                Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UpdateStudent.this, StudentList.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateStudent.this);
                builder.setMessage("Are you sure to delete?");
                builder.setTitle("Alert..!");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        studentDB.deleteData(studentId);
                        Toast.makeText(getApplicationContext(), "Record Deleted", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(UpdateStudent.this, StudentList.class));
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });

                AlertDialog ad = builder.create();
                ad.show();
            }
        });
    }
}