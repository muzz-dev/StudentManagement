package com.muzzdev.studentmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class StudentList extends AppCompatActivity {

    ListView lvStudent;
    ArrayList<String> _list = new ArrayList<String>();
    ArrayAdapter<String> adp;

    StudentDB studentDB = new StudentDB(this, null, null, 1);

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        getSupportActionBar().setTitle("Student List");
        lvStudent = findViewById(R.id.studentList);
        FloatingActionButton fab = findViewById(R.id.fab);


        //View Student Data
        Cursor studentList = studentDB.getStudents();
        if (studentList != null) {
            studentList.moveToFirst();
            do {
                _list.add(studentList.getString(studentList.getColumnIndex("name")).toString());
            } while (studentList.moveToNext());

            adp = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, _list);
            lvStudent.setAdapter(adp);
        }

        //List View Click Event
        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(StudentList.this,"Id: "+ _listIds.get(i),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(StudentList.this,UpdateStudent.class);
//                intent.putExtra("data",_list.get(i));
                intent.putExtra("name", _list.get(i));
                startActivity(intent);
            }
        });

        //Open Add Student
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentList.this, AddStudent.class);
                startActivity(intent);
            }
        });
    }
}