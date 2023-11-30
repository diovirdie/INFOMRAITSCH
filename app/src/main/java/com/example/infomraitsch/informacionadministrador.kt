package com.example.infomraitsch

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.infomraitsch.dataClasses.Publicacion
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class informacionadministrador : AppCompatActivity() {
    private lateinit var icono: ImageView
    private lateinit var txtencabezado: TextView
    private lateinit var txtasunto: TextView
    private lateinit var imageproducto: ImageView
    private lateinit var descripcion: TextView
    private lateinit var btnregresardes: ImageButton
    private lateinit var btneliminar:ImageView
    private lateinit var btnactualizar:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacionadministrador)
        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,  // Puedes ajustar la orientación del degradado
            intArrayOf(Color.parseColor("#5076E4"), Color.parseColor("#67BFDC"))  // Colores de inicio y fin
        )

        // Establece el degradado como fondo de la actividad
        window.decorView.background = gradientDrawable






        //incia codigo del activity
        btneliminar = findViewById(R.id.btneliminar)
        btnactualizar = findViewById(R.id.btnupdate)
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
        btnactualizar.setOnClickListener{
            val intent = Intent(this,UpdateAnuncio::class.java)
            startActivity(intent)
        }
        btneliminar.setOnClickListener{
            val db = FirebaseFirestore.getInstance()
            val usuariosCollection = db.collection("Anuncios")
            val anuncio =intent.getParcelableExtra<Publicacion>("item")
            if(anuncio!=null){
                var id=anuncio.iddocumento
                val documentoRef = usuariosCollection.document(id.toString())
                documentoRef.delete()
                    .addOnSuccessListener {
                        Toast.makeText(this,"Anuncio Eliminado Exitosamente", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, Admin::class.java)
                        startActivity(intent)
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this,"Se produjo un error al tratar de elimnar el archivo porfavor intentelo nuevamente ", Toast.LENGTH_SHORT).show()
                    }

            }
        }


    }
}