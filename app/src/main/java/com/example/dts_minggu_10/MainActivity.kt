package com.example.dts_minggu_10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var db : DatabaseHelper
    private lateinit var adapter : ArrayAdapter<String>
    private var data : ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DatabaseHelper(this)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        loadData()
        val lv : ListView = findViewById(R.id.listview)
        lv.adapter = adapter


        val btnAdd = findViewById<FloatingActionButton>(R.id.btn_add)
        btnAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    fun loadData() {
        val result : ArrayList<String> = db.getAllStudentsList()
        Log.d("TAG", "loadData: " + result)
        data.clear()
        for( r : String in result){
            data.add(r)
        }
        adapter.notifyDataSetChanged()
    }

}