package com.example.infomraitsch

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.infomraitsch.dataClasses.Usuario
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Registro : AppCompatActivity() {
    private lateinit var nombre: TextInputLayout
    private lateinit var apaterno: TextInputLayout
    private lateinit var amaterno: TextInputLayout
    private lateinit var correo: TextInputLayout
    private lateinit var numcontrol: TextInputLayout
    private lateinit var pass: TextInputLayout
    private lateinit var Grupo: TextInputLayout

    //variable que permite establecer la autentificacion
    private lateinit var auth: FirebaseAuth

    private lateinit var btnResgistrar: Button
    private lateinit var btnEstoyRegistrado: MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)//acceso a la base dee datos
        // Crea un objeto GradientDrawable
        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,  // Puedes ajustar la orientación del degradado
            intArrayOf(Color.parseColor("#5076E4"), Color.parseColor("#67BFDC"))  // Colores de inicio y fin
        )

        // Establece el degradado como fondo de la actividad
        window.decorView.background = gradientDrawable


        //inicia codigo
        val baseDatos = Firebase.firestore
        var ncontrol2=""
        nombre = findViewById(R.id.Nombre)
        apaterno= findViewById(R.id.apaterno)
        amaterno= findViewById(R.id.amaterno)
        correo= findViewById(R.id.email_registro)
        pass =findViewById(R.id.password_registro)
        numcontrol= findViewById(R.id.numcontrol)
        btnResgistrar= findViewById(R.id.btnRegistrarDatos)
        btnEstoyRegistrado=findViewById(R.id.btnYaEstoyRegistrado)
        Grupo = findViewById(R.id.Grupo)


        btnResgistrar.setOnClickListener {
            val confirmaDialog = AlertDialog.Builder(it.context)
            confirmaDialog.setTitle("Confirmar Datos")
            confirmaDialog.setMessage(
                """
                Usuario: ${nombre.editText?.text} ${apaterno.editText?.text} ${amaterno.editText?.text}
                Correo: ${correo.editText?.text}
                Contraseña: ${pass.editText?.text}
                
                """".trimIndent()
            )
            ncontrol2 = numcontrol.editText?.text.toString()
            val parte1 = ncontrol2.substring(0, 1).toUpperCase()

            if (parte1=="S" || parte1=="M" || parte1=="G" || parte1=="I" || parte1=="N" || parte1=="T"  || parte1=="B" ){
            confirmaDialog.setPositiveButton("Confirmar") { confirmaDialog, which ->
                val email = correo.editText?.text
                val psw = pass.editText?.text
                val usuario = Usuario(
                    numcontrol.editText?.text.toString().toUpperCase(),
                    email.toString(),
                    Grupo.editText?.text.toString().toUpperCase(),
                    nombre.editText?.text.toString().toUpperCase(),
                    apaterno.editText?.text.toString().toUpperCase(),
                    amaterno.editText?.text.toString().toUpperCase()
                )

                if (email.toString().isNotEmpty() && psw.toString().isNotEmpty()) {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        email.toString(), psw.toString()
                    ).addOnCompleteListener {
                        //crear la coleccion de usuarios
                        if (it.isSuccessful) {
                            val intent = Intent(this, Login::class.java).apply {
                                baseDatos.collection("usuarios")
                                    .document(email.toString())
                                    .set(usuario)

                            }
                            startActivity(intent)

                        } else {
                            showAlert()
                        }

                    }
                }else{
                    Toast.makeText(this,"El numero de control es invalido", Toast.LENGTH_SHORT).show()
                }



            }
            confirmaDialog.setNegativeButton("Cancelar") { confirmaDialog, which ->
                confirmaDialog.cancel()
            }
            confirmaDialog.show()
        }

        }

    }

    private fun showAlert() {
        val alerta= AlertDialog.Builder(this)
        alerta.setTitle("Error")
        alerta.setMessage("Se ha producido un error en la autentificacion!!")
        alerta.setPositiveButton("Aceptar", null)

        alerta.show()
    }
}