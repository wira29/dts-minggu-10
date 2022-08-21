package com.example.dts_minggu_10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddActivity : AppCompatActivity() {
    lateinit var db : DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        db = DatabaseHelper(this)
        val input : EditText = findViewById(R.id.input_nama)
        val btnSave : Button = findViewById(R.id.btn_save)

        btnSave.setOnClickListener {
            if (input.text.isEmpty()){
                input.error = "Nama tidak boleh kosong !"
                input.isFocusable
            }else{
                val res = db.addStudentDetail(input.text.toString())

                if(res >= 0){
                    Toast.makeText(this, "Berhasil menambahkan siswa !", Toast.LENGTH_LONG).show()
                    finish()
                }else{
                    Toast.makeText(this, "Gagal menambahkan siswa !", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}