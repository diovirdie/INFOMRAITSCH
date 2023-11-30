package com.example.infomraitsch.dataClasses

import android.os.Parcel
import android.os.Parcelable

data class Publicacion(
    val carreras: String?,
    val relevancia: String?,
    val asunto:String?,
    val foto:String?,
    val descripcion:String?,
    val icono:String?,

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(carreras)
        parcel.writeString(relevancia)
        parcel.writeString(asunto)
        parcel.writeString(foto)
        parcel.writeString(descripcion)
        parcel.writeString(icono)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Publicacion> {
        override fun createFromParcel(parcel: Parcel): Publicacion {
            return Publicacion(parcel)
        }

        override fun newArray(size: Int): Array<Publicacion?> {
            return arrayOfNulls(size)
        }
    }
}