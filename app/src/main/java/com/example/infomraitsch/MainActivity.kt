package com.example.infomraitsch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.infomraitsch.adaptadores.AdaptadorAnuncio
import com.example.infomraitsch.dataClasses.Publicacion
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

var listaA_G:ArrayList<Publicacion> = ArrayList()
class MainActivity : AppCompatActivity() {
    private lateinit var recyAnnge:RecyclerView

    private lateinit var btnregistro:Button

    private val db = Firebase.firestore

    private lateinit var adaptadorPu: AdaptadorAnuncio
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnregistro= findViewById(R.id.btniniciarhome)


        recyAnnge = findViewById(R.id.rvAnuncioprincipal)

        //comienza codigo de el llenado de recycler
        db.collection("Anuncios").get().addOnSuccessListener {

            for (Anuncios in it){
                val publico="${Anuncios.data.get("carreras")}"

                if (publico =="general"){
                    var documento=Anuncios.id
                    listaA_G.add(
                        Publicacion(
                            "${documento}",
                        "${Anuncios.data.get("carreras")}",
                        "${Anuncios.data.get("relevancia")}",
                        "${Anuncios.data.get("asunto")}",
                        "${Anuncios.data.get("foto")}",
                        "${Anuncios.data.get("descripcion")}",
                            "${Anuncios.data.get("icono")}")
                    )


                }
            }
            adaptadorPu= AdaptadorAnuncio(listaA_G, this)
            recyAnnge.adapter =  adaptadorPu
            recyAnnge.layoutManager = LinearLayoutManager(this)
            adaptadorPu.onProductoClick= {
                val intent = Intent(this,infoanunciogeneral::class.java)

                ///con esto nos llevamos toda la informacion de producto seleccionado
                intent.putExtra("item",it)
                startActivity(intent)
            }



        }




        btnregistro.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }



    }
}