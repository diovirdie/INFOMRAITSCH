package com.example.infomraitsch


import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import com.google.firebase.storage.FirebaseStorage
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.*
import com.example.infomraitsch.dataClasses.Publicacion
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.UUID
var uri2 =""

class crearAnuncio : AppCompatActivity() {
    private lateinit var swti:Switch
    private lateinit var cas:RadioButton
    private lateinit var cam:RadioButton
    private lateinit var cag:RadioButton
    private lateinit var cai:RadioButton
    private lateinit var can:RadioButton
    private lateinit var cab:RadioButton
    private lateinit var cay:RadioButton
    private lateinit var btncargar:Button
    private lateinit var im:ImageView
    private lateinit var asun: TextInputLayout
    private lateinit var txtDescripcion:EditText
    private lateinit var btnsend: ImageButton
    private lateinit var brnregresar:ImageButton

    //variables de carreras
    var sistemas=0
    var mecatronica=0
    var gestion=0
    var industrial=0
    var nano=0
    var tics=0
    var bioquimica=0
    var general =0

    //variable icono
    var icono =""

    //variables de relvancia
    var relevancia = ""


    val pickMedia = registerForActivityResult(PickVisualMedia()){uri ->
        if(uri!=null){
            im.setImageURI(uri)

            uploadImageToFirebaseStorage(uri)
          /*  val storage = Firebase.storage
            var storageRef = storage.reference
            var file = Uri.fromFile(File("${uri}"))
            val riversRef = storageRef.child("/images/")
            var uploadTask = riversRef.putFile(file)

            // Register observers to listen for when the download is done or if it fails
            uploadTask.addOnFailureListener {
                // Handle unsuccessful uploads
                Toast.makeText(this,"Bienbenido ${uploadTask}!!", Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener { taskSnapshot ->
                // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                // ...
            }*/


        }else{
            Log.i("aris", "No seleccionado")
        }

    }

    private var radioButtonHabilitado = false // Variable para controlar el estado del RadioButton
    lateinit var storage: FirebaseStorage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_anuncio)
        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,  // Puedes ajustar la orientación del degradado
            intArrayOf(Color.parseColor("#5076E4"), Color.parseColor("#67BFDC"))  // Colores de inicio y fin
        )

        // Establece el degradado como fondo de la actividad
        window.decorView.background = gradientDrawable
        storage = Firebase.storage
        swti = findViewById(R.id.swirch_g)
        cas = findViewById(R.id.car_s)
        cam = findViewById(R.id.car_m)
        cag = findViewById(R.id.car_g)
        cai = findViewById(R.id.car_i)
        can = findViewById(R.id.car_n)
        cab = findViewById(R.id.car_b)
        cay = findViewById(R.id.car_y)
        im = findViewById(R.id.imagen)
        btnsend = findViewById(R.id.btnsend)
        txtDescripcion = findViewById(R.id.txtDescripcion1)
        txtDescripcion.setTextColor(Color.BLACK)
        asun = findViewById(R.id.asunto)
        general =1
        val baseDatos = Firebase.firestore
        brnregresar = findViewById(R.id.btnregresara)
        brnregresar.setOnClickListener {
            val intent = Intent(this,Admin::class.java)
            startActivity(intent)
        }



        btnsend.setOnClickListener {
            carreras()
            verificarrb()

            if (sistemas == 1){

                val publicacion = Publicacion(
                    "Sistemas",
                    "${relevancia.toString()}",
                    "${asun.editText!!.text.toString()}",
                    "${uri2.toString()}",
                    "${txtDescripcion.text.toString()}",
                    "${icono.toString()}"
                )
                baseDatos.collection("Anuncios")
                    .document().set(publicacion)
            }
            if (mecatronica == 1){

                val publicacion = Publicacion(
                    "mecatronica",
                    "${relevancia.toString()}",
                    "${asun.editText!!.text.toString()}",
                    "${uri2.toString()}",
                    "${txtDescripcion.text.toString()}",
                    "${icono.toString()}"

                )
                baseDatos.collection("Anuncios")
                    .document().set(publicacion)
            }
            if (gestion == 1){

                val publicacion = Publicacion(
                    "gestion",
                    "${relevancia.toString()}",
                    "${asun.editText!!.text.toString()}",
                    "${uri2.toString()}",
                    "${txtDescripcion.text.toString()}",
                    "${icono.toString()}"
                )
                baseDatos.collection("Anuncios")
                    .document().set(publicacion)
            }
            if (industrial == 1){

                val publicacion = Publicacion(
                    "industrial",
                    "${relevancia.toString()}",
                    "${asun.editText!!.text.toString()}",
                    "${uri2.toString()}",
                    "${txtDescripcion.text.toString()}",
                    "${icono.toString()}"
                )
                baseDatos.collection("Anuncios")
                    .document().set(publicacion)
            }
            if (nano == 1){

                val publicacion = Publicacion(
                    "nano",
                    "${relevancia.toString()}",
                    "${asun.editText!!.text.toString()}",
                    "${uri2.toString()}",
                    "${txtDescripcion.text.toString()}",
                    "${icono.toString()}"
                )
                baseDatos.collection("Anuncios")
                    .document().set(publicacion)
            }
            if (tics == 1){

                val publicacion = Publicacion(
                    "tics",
                    "${relevancia.toString()}",
                    "${asun.editText!!.text.toString()}",
                    "${uri2.toString()}",
                    "${txtDescripcion.text.toString()}",
                    "${icono.toString()}"
                )
                baseDatos.collection("Anuncios")
                    .document().set(publicacion)
            }
            if (bioquimica == 1){

                val publicacion = Publicacion(
                    "bioquimica",
                    "${relevancia.toString()}",
                    "${asun.editText!!.text.toString()}",
                    "${uri2.toString()}",
                    "${txtDescripcion.text.toString()}",
                    "${icono.toString()}"
                )
                baseDatos.collection("Anuncios")
                    .document().set(publicacion)
            }
            if (general == 1){

                val publicacion = Publicacion(
                    "general",
                    "${relevancia.toString()}",
                    "${asun.editText!!.text.toString()}",
                    "${uri2.toString()}",
                    "${txtDescripcion.text.toString()}",
                    "${icono.toString()}"
                )
                baseDatos.collection("Anuncios")
                    .document().set(publicacion)
            }
            val intent = Intent(this, Admin::class.java)
            startActivity(intent)

        }
        active()
        btncargar = findViewById(R.id.btncargararchivo)
        swti.setOnClickListener{
            if(swti.isChecked()){

                radioButtonHabilitado = false
                active()
                general =1
                actune()


            }else{
                radioButtonHabilitado = true
                active()
                general =0
              actune()

            }

        }


        btncargar.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))

        }
    }
    private fun uploadImageToFirebaseStorage(uri: Uri) {
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference

        // Nombre único para el archivo en Storage
        val fileName = UUID.randomUUID().toString()
        val imageRef = storageRef.child("images/$fileName")

        // Subir la imagen
        imageRef.putFile(uri)
            .addOnSuccessListener {
                // La imagen se subió exitosamente
                // Aquí puedes obtener la URL de descarga
                imageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                    val downloadUrl = downloadUri.toString()
                    uri2=downloadUrl.toString()
                    // Aquí puedes usar la URL de descarga como desees
                }
            }
            .addOnFailureListener { exception ->
                // La carga falló
            }
    }

    fun actune(){
        cas.isChecked=false
        cam.isChecked=false
        cag.isChecked=false
        cai.isChecked=false
        can.isChecked=false
        cab.isChecked=false
        cay.isChecked=false

    }
    fun actupo(){
        cas.isChecked=true
        cam.isChecked=true
        cag.isChecked=true
        cai.isChecked=true
        can.isChecked=true
        cab.isChecked=true
        cay.isChecked=true

    }

    fun carreras(){
        if (cas.isChecked) {
            sistemas = 1
        } else {
            sistemas = 0
        }

        //inicia
        if (cam.isChecked) {
            mecatronica = 1
        } else {
            mecatronica = 0
        }
        //inicia
        if (cag.isChecked) {
            gestion = 1
        } else {
            gestion = 0
        }
        //inicia
        if (cai.isChecked) {
            industrial = 1
        } else {
            industrial = 0
        }
        //inicia
        if (can.isChecked) {
            nano = 1
        } else {
            nano = 0
        }
        //inicia
        if (cab.isChecked) {
            bioquimica = 1

        } else {
            bioquimica =0
        }
        //inicia
        if (cay.isChecked) {
            tics = 1
        } else {
            tics = 0

        }
    }
    fun verificarrb(){
        val radioGroup: RadioGroup = findViewById(R.id.rgrelevancia)
        val radioButtonId = radioGroup.checkedRadioButtonId
        if (radioButtonId != -1) {
            // Al menos un RadioButton está seleccionado
            // Puedes realizar acciones según el RadioButton seleccionado

            when (radioButtonId) {
                R.id.rbAlta -> {
                    relevancia = "Alta"
                    icono = "https://firebasestorage.googleapis.com/v0/b/infomraitsch.appspot.com/o/iconos%2Fimportante.png?alt=media&token=707b8fc4-b296-4142-9cf2-e1fefb246c1c"
                }
                R.id.rbMedia -> {
                    relevancia = "Media"
                    icono = "https://firebasestorage.googleapis.com/v0/b/infomraitsch.appspot.com/o/iconos%2Fmedio.png?alt=media&token=0e10b461-ed8f-4b27-809b-8e01fae62672"
                }
                R.id.rbBaja -> {
                    relevancia = "Baja"
                    icono = "https://firebasestorage.googleapis.com/v0/b/infomraitsch.appspot.com/o/iconos%2Fbaja.png?alt=media&token=f3fc2565-fdc0-4b81-8623-2d32418a9c1f"
                }



            }
        } else {
            // Ningún RadioButton está seleccionado
            // Puedes manejar esto según tus necesidades
        }

    }
    fun active(){
        cas.visibility = if (radioButtonHabilitado) View.VISIBLE else View.GONE
        cam.visibility = if (radioButtonHabilitado) View.VISIBLE else View.GONE
        cag.visibility = if (radioButtonHabilitado) View.VISIBLE else View.GONE
        cai.visibility = if (radioButtonHabilitado) View.VISIBLE else View.GONE
        can.visibility = if (radioButtonHabilitado) View.VISIBLE else View.GONE
        cab.visibility = if (radioButtonHabilitado) View.VISIBLE else View.GONE
        cay.visibility = if (radioButtonHabilitado) View.VISIBLE else View.GONE
    }
}