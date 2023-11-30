package com.example.infomraitsch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

private lateinit var correoRecuper: TextInputLayout
private lateinit var btnrecuperar: Button
private lateinit var btnRegresar: Button
private  var auth= FirebaseAuth.getInstance()
class RecuperrarPass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperrar_pass)
        correoRecuper= findViewById(R.id.email_recuperar)
        btnrecuperar = findViewById(R.id.btnRecuperarPass)
        btnRegresar =findViewById(R.id.btnRegresar_Recuperar)

        btnrecuperar.setOnClickListener {
            if(correoRecuper.editText?.text.toString().isNotEmpty()||
                Patterns.EMAIL_ADDRESS.matcher(correoRecuper.toString()).matches()){
                correoRecuper.error=null
                enviarCORREORecuperacio()
            }else {
                correoRecuper.error="Correo Necesario"
                Toast.makeText(this,"Correo Invalido!!", Toast.LENGTH_SHORT).show()
            }


        }
        btnRegresar.setOnClickListener {
            val intent= Intent(this,Login::class.java)
            startActivity(intent)
        }

    }

    private fun enviarCORREORecuperacio() {
        auth.sendPasswordResetEmail(correoRecuper.editText?.text.toString())
            .addOnCompleteListener{
                Toast.makeText(this,"${correoRecuper.editText?.text.toString()} Enviado!!", Toast.LENGTH_SHORT).show()
                val intent= Intent(this,Login::class.java)
                startActivity(intent)
            }
    }
}