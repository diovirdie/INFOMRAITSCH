package com.example.infomraitsch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class butoncrear : AppCompatActivity() {
    private lateinit var btncre:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_butoncrear)
        btncre = findViewById(R.id.btncrearAnuncio)
        btncre.setOnClickListener {
            val intent = Intent(this, crearAnuncio::class.java)
            startActivity(intent)
        }
    }
}