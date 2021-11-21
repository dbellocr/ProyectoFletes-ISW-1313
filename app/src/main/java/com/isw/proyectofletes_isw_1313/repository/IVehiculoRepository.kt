package com.isw.proyectofletes_isw_1313.repository

import com.isw.proyectofletes_isw_1313.model.Vehiculo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Cambiar el puerto aqui Erick
const val BASE_URL = "http://10.0.2.2:4478/api/"

interface IVehiculoRepository {

    @GET("vehiculos")
    fun getCamiones(): Call<List<Vehiculo>>

    companion object{
        operator fun invoke() : IVehiculoRepository{
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IVehiculoRepository::class.java)
        }
    }
}