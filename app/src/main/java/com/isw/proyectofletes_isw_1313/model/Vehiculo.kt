package com.isw.proyectofletes_isw_1313.model

import com.google.android.gms.maps.model.LatLng

data class Vehiculo (
    // Profe, los atributos tienen que iniciar en minuscula para poder mapearlo con el nombre de las propiedades que están en el API
    val id: String,
    val modelo: String,
    val capacidad: Int,
    val latitud: String,
    val longiud: String,
    val conductor: String,
    var urlFotografía: String
) /*{
    init {
        ubicacion = LatLng(Latitud.toDouble(), Longiud.toDouble())
    }
}
        */