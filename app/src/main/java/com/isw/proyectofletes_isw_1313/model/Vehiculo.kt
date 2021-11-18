package com.isw.proyectofletes_isw_1313.model

import com.google.android.gms.maps.model.LatLng

class Vehiculo (
    // Profe, los atributos tienen que iniciar en minuscula para poder mapearlo con el nombre de las propiedades que están en el API
    val Id: Int,
    val Modelo: String,
    val Capacidad: Int,
    val Latitud: String,
    val Longiud: String,
    val Conductor: String,
    var ubicacion: LatLng,
) {
    init {
        ubicacion = LatLng(Latitud.toDouble(), Longiud.toDouble())
    }
}