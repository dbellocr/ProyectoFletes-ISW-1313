package com.isw.proyectofletes_isw_1313.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.isw.proyectofletes_isw_1313.R
import com.isw.proyectofletes_isw_1313.adapter.PlaceResponse
import com.isw.proyectofletes_isw_1313.adapter.toPlace
import com.isw.proyectofletes_isw_1313.model.Place
import java.io.InputStream
import java.io.InputStreamReader

class PlacesReader(private val context: Context) {

    private val gson = Gson()

    private val inputStream: InputStream
        get() = context.resources.openRawResource(R.raw.places)

    fun read(): List<Place> {
        val itemType = object : TypeToken<List<PlaceResponse>>() {}.type
        val reader = InputStreamReader(inputStream)
        return gson.fromJson<List<PlaceResponse>>(reader, itemType).map {
            it.toPlace()
        }
    }
}