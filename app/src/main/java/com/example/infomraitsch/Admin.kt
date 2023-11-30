package com.example.infomraitsch

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.infomraitsch.adaptadores.AdaptadorAnuncio
import com.example.infomraitsch.dataClasses.Publicacion
import com.example.infomraitsch.dataClasses.Usuario
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

var listaA:ArrayList<Publicacion> = ArrayList()
class Admin : AppCompatActivity() {
    private lateinit var recyAnnge: RecyclerView
    private val db = Firebase.firestore
    private lateinit var botonmenu:ImageButton
    private lateinit var botonagregar:ImageButton
    private lateinit var adaptadorPu: AdaptadorAnuncio
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        botonmenu = findViewById(R.id.btnmenu)
        botonagregar = findViewById(R.id.btnAgregar)
        listaA.clear()

        //codigo colorerar
        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,  // Puedes ajustar la orientación del degradado
            intArrayOf(Color.parseColor("#5076E4"), Color.parseColor("#67BFDC"))  // Colores de inicio y fin
        )

        // Establece el degradado como fondo de la actividad
        window.decorView.background = gradientDrawable




        botonagregar.setOnClickListener {
            val intent = Intent(this, crearAnuncio::class.java)
            startActivity(intent)

        }
        botonmenu.setOnClickListener {
            showPopupMenu(it)
        }
        recyAnnge = findViewById(R.id.rvAnuncioprincipal)
        //comienza codigo de el llenado de recycler
        db.collection("Anuncios").get().addOnSuccessListener {

            for (Anuncios in it){
                    listaA_G.add(
                        Publicacion(
                            "${Anuncios.data.get("carreras")}",
                            "${Anuncios.data.get("relevancia")}",
                            "${Anuncios.data.get("asunto")}",
                            "${Anuncios.data.get("foto")}",
                            "${Anuncios.data.get("descripcion")}",
                            "${Anuncios.data.get("icono")}"
                        ))


            }
            adaptadorPu= AdaptadorAnuncio(listaA_G, this)
            recyAnnge.adapter =  adaptadorPu
            recyAnnge.layoutManager = LinearLayoutManager(this)



        }
    }
    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view, Gravity.END)
        popupMenu.inflate(R.menu.menuadmin)

        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.menu_item1 -> {
                    val intent = Intent(this,Admin::class.java)
                    startActivity(intent)

                    true
                }
                R.id.menu_item2 -> {

                    true
                }
                R.id.menu_item3 -> {


                    true
                }
                R.id.menu_item4 -> {
                    val intent= Intent(this, Login::class.java)
                    usuarioApp = Usuario("","","","","","")
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }
}