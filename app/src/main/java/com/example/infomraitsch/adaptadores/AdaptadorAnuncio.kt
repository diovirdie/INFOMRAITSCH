package com.example.infomraitsch.adaptadores

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.infomraitsch.R
import com.example.infomraitsch.dataClasses.Publicacion
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class AdaptadorAnuncio  (private val items: List<Publicacion>, val activity: Activity) : RecyclerView.Adapter<AdaptadorAnuncio.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewItem: TextView = itemView.findViewById(R.id.asunto)
        val descr: TextView = itemView.findViewById(R.id.textoencabezadoan)
        val imagen:ImageView = itemView.findViewById(R.id.imagenProducto)
        val icono:ImageView = itemView.findViewById(R.id.iconoa)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_anuncio, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.textViewItem.text = item.asunto
        holder.descr.text = item.relevancia
        Glide.with(holder.itemView.context)
            .load(item.foto)
            .into(holder.imagen)
        Glide.with(holder.itemView.context)
            .load(item.icono)
            .into(holder.icono)

    }

    override fun getItemCount(): Int {
        return items.size
    }
}