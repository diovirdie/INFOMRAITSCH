package com.example.infomraitsch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.infomraitsch.dataClasses.Publicacion

class UpdateAnuncio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_anuncio)
        val anuncio =intent.getParcelableExtra<Publicacion>("item")


    }
}