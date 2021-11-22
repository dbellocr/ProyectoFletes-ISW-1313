package com.isw.proyectofletes_isw_1313.model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class Vehiculo  (
    val id: String,
    val modelo: String,
    val capacidad: Int,
    val latitud: String,
    val longitud: String,
    val conductor: String,
    var urlFotografía: String
): ClusterItem {
    override fun getPosition(): LatLng {
        return  LatLng(latitud.toDouble(), longitud.toDouble())
    }

    override fun getTitle(): String {
      return id
    }

    override fun getSnippet(): String {
        return modelo
    }
}