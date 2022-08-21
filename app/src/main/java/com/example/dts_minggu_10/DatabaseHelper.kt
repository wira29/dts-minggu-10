package com.example.dts_minggu_10

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi

class DatabaseHelper(
    context: Context?
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {



    companion object {
        private val DATABASE_NAME : String = "student_database";
        private val DATABASE_VERSION : Int = 1;
        private val TABLE_STUDENTS : String = "students";
        private val KEY_ID : String = "id";
        private val KEY_FIRSTNAME : String = "name"

        private val CREATE_TABLE_STUDENTS : String = "CREATE TABLE " + TABLE_STUDENTS  + "(" + KEY_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FIRSTNAME + " TEXT );"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_STUDENTS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS '" + TABLE_STUDENTS +"'")
        onCreate(db)
    }

    fun addStudentDetail(student : String) : Long {
        val db : SQLiteDatabase = this.writableDatabase
        val values : ContentValues = ContentValues()
        values.put(KEY_FIRSTNAME, student)

        return db.insert(TABLE_STUDENTS, null, values)
    }

    @SuppressLint("Range")
    fun getAllStudentsList(): ArrayList<String> {
        var studentList : ArrayList<String> = arrayListOf()
        var name : String = ""
        val selectQuery : String = "SELECT * FROM " + TABLE_STUDENTS
        val db : SQLiteDatabase = this.readableDatabase
        val c : Cursor = db.rawQuery(selectQuery, null)

        if(c.moveToFirst()){
            do {
                name = c.getString(c.getColumnIndex(KEY_FIRSTNAME))
                studentList.add(name)
            }while (c.moveToNext())
        }
        return  studentList
    }
}