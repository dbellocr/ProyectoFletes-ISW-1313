package com.isw.proyectofletes_isw_1313.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isw.proyectofletes_isw_1313.model.Reservacion
import kotlinx.coroutines.launch
import retrofit2.http.POST

class ReservacionViewModel (private val repository: IReservacionRepository): ViewModel() {
    val myResponse: MutableLiveData<Reservacion> = MutableLiveData()

    fun agregarReservacion(post: Reservacion){
        viewModelScope.launch {
            val response=repository.agregarReservacion(post)
            //myResponse.value= response
        }
    }
    /*fun getPost(){
        viewModelScope.launch {
            val response=repository.getReservaciones()
            myResponse.value= response
        }
    }*/
}