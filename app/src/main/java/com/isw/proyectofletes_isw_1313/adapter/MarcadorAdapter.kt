package com.isw.proyectofletes_isw_1313.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.isw.proyectofletes_isw_1313.R
import com.isw.proyectofletes_isw_1313.model.Place
import com.isw.proyectofletes_isw_1313.model.Vehiculo

class MarcadorAdapter(private val context: Context)  : GoogleMap.InfoWindowAdapter  {
    override fun getInfoWindow(marker: Marker?): View? {

        val place = marker?.tag as? Vehiculo ?: return null

        val view = LayoutInflater.from(context).inflate(
            R.layout.marcador_camion, null
        )
        view.findViewById<TextView>(
            R.id.tvPlaca
        ).text = "Placa: "+  place.id

        view.findViewById<TextView>(
            R.id.tvConductor
        ).text = "Conducido por: " + place.conductor

        view.findViewById<TextView>(
            R.id.tvModelo
        ).text = place.modelo

        val imageView = view.findViewById<ImageView>(
            R.id.imagenVehiculo
        )

        Glide.with(view).load(place.urlFotografía).into(imageView)

        return view
    }

    override fun getInfoContents(p0: Marker?): View? {
       return null
    }

}