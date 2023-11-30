package com.example.infomraitsch

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.infomraitsch.adaptadores.AdaptadorAnuncio
import com.example.infomraitsch.dataClasses.Publicacion
import com.example.infomraitsch.dataClasses.Usuario
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
var listaalumno:ArrayList<Publicacion> = ArrayList()
class Alumno : AppCompatActivity() {
    private lateinit var grupo:RadioGroup
    private lateinit var radiocar:RadioButton
    private lateinit var radioGen:RadioButton
    private lateinit var radioRel:RadioButton
    private lateinit var recycler:RecyclerView
    private val db = Firebase.firestore
    private lateinit var adaptadorPu: AdaptadorAnuncio
    private lateinit var btnsalir:ImageButton






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alumno)

        //codigo colorerar
        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,  // Puedes ajustar la orientación del degradado
            intArrayOf(Color.parseColor("#2af598"), Color.parseColor("#009efd"))  // Colores de inicio y fin
        )

        // Establece el degradado como fondo de la actividad
        window.decorView.background = gradientDrawable







        //ininicia codigo de la aplicacion
        grupo = findViewById(R.id.rgrelevancia)
        radiocar = findViewById(R.id.carrera)
        radioGen = findViewById(R.id.general)
        radioRel = findViewById(R.id.Alta)
        recycler = findViewById(R.id.rvAnuncioprincipal)
        listaalumno.clear()
        listaA.clear()
        listaA_G.clear()
        btnsalir = findViewById(R.id.BTNSALIR)
        limpiar()
        general()



        radioGen.setOnClickListener {
            Toast.makeText(this,"Filtrando por general ", Toast.LENGTH_SHORT).show()
            general()
        }
        radiocar.setOnClickListener {
            Toast.makeText(this,"Filtrando por carrera", Toast.LENGTH_SHORT).show()
            carrera()
        }
        radioRel.setOnClickListener {
            Toast.makeText(this,"Filtrando por relevancia alta  ", Toast.LENGTH_SHORT).show()
            relevancia()
        }


        btnsalir.setOnClickListener {
            val intent= Intent(this, Login::class.java)
            usuarioApp = Usuario("","","","","","")
            startActivity(intent)
            super.onDestroy()
        }







    }
    fun general() {
        limpiar()


        //comienza codigo de el llenado de recycler
        db.collection("Anuncios").get().addOnSuccessListener {

            for (Anuncios in it) {
                val publico = "${Anuncios.data.get("carreras")}"

                if (publico == "general") {
                    var documento=Anuncios.id
                    listaA_G.add(
                        Publicacion(
                            "${documento}",
                            "${Anuncios.data.get("carreras")}",
                            "${Anuncios.data.get("relevancia")}",
                            "${Anuncios.data.get("asunto")}",
                            "${Anuncios.data.get("foto")}",
                            "${Anuncios.data.get("descripcion")}",
                            "${Anuncios.data.get("icono")}"
                        )
                    )


                }
            }
            adaptadorPu = AdaptadorAnuncio(listaA_G, this)
            recycler.adapter = adaptadorPu
            recycler.layoutManager = LinearLayoutManager(this)
            adaptadorPu.onProductoClick= {
                val intent = Intent(this,InfoAnuncio::class.java)

                ///con esto nos llevamos toda la informacion de producto seleccionado
                intent.putExtra("item",it)
                startActivity(intent)
            }


        }
    }
    fun carrera(){
        limpiar()
        //comienza codigo de el llenado de recycler
        var carrera = usuarioApp.NumeroControl
        val parte1 = carrera.substring(0, 1).toUpperCase()

        db.collection("Anuncios").get().addOnSuccessListener {

            for (Anuncios in it) {
                val publico = "${Anuncios.data.get("carreras")}"
                val parte2 = publico.substring(0, 1).toUpperCase()


                if (parte2 == parte1) {
                    var documento=Anuncios.id

                    listaA_G.add(
                        Publicacion(
                            "${documento}",
                            "${Anuncios.data.get("carreras")}",
                            "${Anuncios.data.get("relevancia")}",
                            "${Anuncios.data.get("asunto")}",
                            "${Anuncios.data.get("foto")}",
                            "${Anuncios.data.get("descripcion")}",
                            "${Anuncios.data.get("icono")}"
                        )
                    )


                }
            }
            adaptadorPu = AdaptadorAnuncio(listaA_G, this)
            recycler.adapter = adaptadorPu
            recycler.layoutManager = LinearLayoutManager(this)
            adaptadorPu.onProductoClick= {
                val intent = Intent(this,InfoAnuncio::class.java)

                ///con esto nos llevamos toda la informacion de producto seleccionado
                intent.putExtra("item",it)
                startActivity(intent)
            }


        }

    }
    fun relevancia(){
        limpiar()
        limpiar()
        //comienza codigo de el llenado de recycler


        db.collection("Anuncios").get().addOnSuccessListener {

            for (Anuncios in it) {
                val publico = "${Anuncios.data.get("relevancia")}"



                if (publico == "Alta") {
                    var documento=Anuncios.id


                    listaA_G.add(
                        Publicacion(
                            "${documento}",
                            "${Anuncios.data.get("carreras")}",
                            "${Anuncios.data.get("relevancia")}",
                            "${Anuncios.data.get("asunto")}",
                            "${Anuncios.data.get("foto")}",
                            "${Anuncios.data.get("descripcion")}",
                            "${Anuncios.data.get("icono")}"
                        )
                    )


                }
            }
            adaptadorPu = AdaptadorAnuncio(listaA_G, this)
            recycler.adapter = adaptadorPu
            recycler.layoutManager = LinearLayoutManager(this)
            adaptadorPu.onProductoClick= {
                val intent = Intent(this,InfoAnuncio::class.java)

                ///con esto nos llevamos toda la informacion de producto seleccionado
                intent.putExtra("item",it)
                startActivity(intent)
            }


        }

    }
    fun limpiar(){
        listaalumno.clear()
        listaA.clear()
        listaA_G.clear()
    }
}