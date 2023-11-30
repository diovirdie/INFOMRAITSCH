package com.example.infomraitsch

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.infomraitsch.dataClasses.Usuario
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore


lateinit var usuarioApp: Usuario

class Login : AppCompatActivity() {
    private lateinit var  btnRegistrar: MaterialButton
    private lateinit var  btnIngresar: Button
    private lateinit var correo: TextInputLayout
    private lateinit var botonOlvidar: Button
    private lateinit var password: TextInputLayout
    private lateinit var auth: FirebaseAuth
    private lateinit var btnsg: SignInButton
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var ncontrol=""

        // Crea un objeto GradientDrawable
        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,  // Puedes ajustar la orientación del degradado
            intArrayOf(Color.parseColor("#5076E4"), Color.parseColor("#67BFDC"))  // Colores de inicio y fin
        )

        // Establece el degradado como fondo de la actividad
        window.decorView.background = gradientDrawable


        //INICIA CODIGO DE LOGEO
        auth= Firebase.auth
        mAuth=FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        //acceso a la base de datos
        val baseDatos = Firebase.firestore


        btnRegistrar=findViewById(R.id.btnRegistrar)
        btnIngresar = findViewById(R.id.btningresar)
        correo=findViewById(R.id.email)
        password=findViewById(R.id.password)
        botonOlvidar=findViewById(R.id.btnOlvidar)

        botonOlvidar.setOnClickListener {
            val intent= Intent(this,RecuperrarPass::class.java)
            startActivity(intent)
        }
        btnIngresar.setOnClickListener {
            if (correo.editText?.text.toString().isNotEmpty() && password.editText?.text.toString().isNotEmpty() ){

                auth.signInWithEmailAndPassword(
                    correo.editText?.text.toString(),
                    password.editText?.text.toString()).addOnCompleteListener{
                    if(it.isSuccessful){

                        baseDatos.collection("usuarios").whereEqualTo("correo",correo.editText?.text.toString())
                            .get()
                            //nos aseguramos que la informacion anterior se aya cargado correctamente

                            .addOnSuccessListener{documents ->
                                for(document in documents){
                                    usuarioApp = Usuario(
                                        "${document.data.get("numeroControl")}",
                                        "${document.data.get("correo")}",
                                        "${document.data.get("grupo")}",
                                        "${document.data.get("nombre")}",
                                        "${document.data.get("apaterno")}",
                                        "${document.data.get("amaterno")}",
                                    )
                                  ncontrol ="${document.data.get("numeroControl")}"
                                }

                                val parte1 = ncontrol.substring(0, 1)
                                if (parte1=="S" || parte1=="M" || parte1=="G" || parte1=="I" || parte1=="N" || parte1=="T"  || parte1=="B"    ){
                                    val intent = Intent(this,Alumno::class.java)
                                    startActivity(intent)
                                    Toast.makeText(this,"Bienbenido ${ncontrol}!!", Toast.LENGTH_SHORT).show()
                                }else{
                                    Toast.makeText(this,"${ncontrol}", Toast.LENGTH_SHORT).show()

                                }
                                if(parte1=="A" ){
                                    val intent = Intent(this,Admin::class.java)
                                    startActivity(intent)


                                }


                            }
                    }else{
                        notificacion()
                    }
                }



            }
        }
        btnRegistrar.setOnClickListener{
            val intent = Intent(this,Registro::class.java)
            startActivity(intent)
        }

    }



    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)

            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val dashboardActivity = Intent(this, Alumno::class.java)
                    startActivity(dashboardActivity)
                    this.finish()


                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
        Log.d(TAG,"Got ID token")
    }


    private fun updateUI(user: FirebaseUser?) {
        if(user!= null){
            irhome();
        }


    }

    private fun irhome() {

    }


    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }



    private fun notificacion() {
        val alerta= AlertDialog.Builder(this)
        alerta.setTitle("Error")
        alerta.setMessage("Se ha producido un error en la autentificacion!!")
        alerta.setPositiveButton("Aceptar", null)
        alerta.show()
    }






}