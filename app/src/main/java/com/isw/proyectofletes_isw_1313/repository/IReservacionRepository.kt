package com.isw.proyectofletes_isw_1313.repository

import android.graphics.PostProcessor
import androidx.lifecycle.viewModelScope
import com.isw.proyectofletes_isw_1313.api.RetrofitInstance
import com.isw.proyectofletes_isw_1313.model.Reservacion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

// Cambiar el puerto aqui Erick
const val BASE_URL1 = "http://10.0.2.2:4478/api/"

interface IReservacionRepository {

    @GET("reservacion")
    fun getReservaciones(): Call<List<Reservacion>>
    companion object {
        operator fun invoke(): IReservacionRepository {
            return Retrofit.Builder().baseUrl(BASE_URL1)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IReservacionRepository::class.java)
        }
    }

    suspend fun agregarReservacion(post: Reservacion): Response<Reservacion>{
        return RetrofitInstance.api.agregarReservacion(post)
    }

}