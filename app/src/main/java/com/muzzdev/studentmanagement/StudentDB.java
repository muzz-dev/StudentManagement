package com.muzzdev.studentmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class StudentDB extends SQLiteOpenHelper {

    public StudentDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "studentDB", factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table teacher(teacherId Integer primary key autoincrement, username text, password text)");
        sqLiteDatabase.execSQL("create table student(studentId Integer primary key autoincrement, name text, address text, gender text, phone text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }

    public boolean Login(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from teacher where username = ? and password = ?", new String[]{username, password});
        if (res.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor getStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from student", null);
        return res;
    }

    public void addStudent(String name, String address, String gender, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("address", address);
        values.put("gender", gender);
        values.put("phone", phone);
        db.insert("student", null, values);
    }

    public Cursor getStudentByName(String name) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from student where name =?", new String[]{name});
        return res;
    }

    public void deleteData(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("student","name=?",new String[]{name});
        db.close();
    }

}
