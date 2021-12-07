package com.isw.proyectofletes_isw_1313.api

import com.isw.proyectofletes_isw_1313.model.Reservacion
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SimpleApi {
    @POST("reservacion")
    suspend fun agregarReservacion(
        @Body post: Reservacion
    ): Response<Reservacion>
}