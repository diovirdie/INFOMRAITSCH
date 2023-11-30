package com.example.infomraitsch

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.infomraitsch.dataClasses.Publicacion
import com.google.firebase.storage.FirebaseStorage

class InfoAnuncio : AppCompatActivity() {
    private lateinit var icono:ImageView
    private lateinit var txtencabezado:TextView
    private lateinit var txtasunto:TextView
    private lateinit var imageproducto:ImageView
    private lateinit var descripcion:TextView
    private lateinit var btnregresardes:ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_anuncio)
        //codigo colorerar
        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,  // Puedes ajustar la orientación del degradado
            intArrayOf(Color.parseColor("#5076E4"), Color.parseColor("#67BFDC"))  // Colores de inicio y fin
        )

        // Establece el degradado como fondo de la actividad
        window.decorView.background = gradientDrawable






        //incia codigo del activity

        icono = findViewById(R.id.iconoa)
        txtencabezado = findViewById(R.id.textoencabezadoan)
        txtasunto = findViewById(R.id.asunto)
        imageproducto = findViewById(R.id.imagenProducto)
        descripcion = findViewById(R.id.Descripcion)
        btnregresardes = findViewById(R.id.btnregresarinfo)
        btnregresardes.setOnClickListener {
            val intent = Intent(this, Admin::class.java)
            startActivity(intent)
        }
        val anuncio =intent.getParcelableExtra<Publicacion>("item")
        if(anuncio!=null){
            val storage = FirebaseStorage.getInstance()
            val storageRef = storage.reference
            txtencabezado.text = anuncio.relevancia
            txtasunto.text = anuncio.asunto
            descripcion.text = anuncio.descripcion
            Glide.with(this)
                .load(anuncio.foto)
                .into(imageproducto)
            Glide.with(this)
                .load(anuncio.icono)
                .into(icono)


        }


    }
}