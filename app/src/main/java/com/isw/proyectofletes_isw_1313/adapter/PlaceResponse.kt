package com.isw.proyectofletes_isw_1313.adapter

import com.google.android.gms.maps.model.LatLng
import com.isw.proyectofletes_isw_1313.model.Place

data class PlaceResponse(
    val geometry: Geometry,
    val name: String,
    val vicinity: String,
    val rating: Float
) {

    data class Geometry(
        val location: GeometryLocation
    )

    data class GeometryLocation(
        val lat: Double,
        val lng: Double
    )
}

fun PlaceResponse.toPlace(): Place = Place(
    name = name,
    latLng = LatLng(geometry.location.lat, geometry.location.lng),
    address = vicinity,
    rating = rating
)