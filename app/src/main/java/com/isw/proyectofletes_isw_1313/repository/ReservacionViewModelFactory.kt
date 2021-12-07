package com.isw.proyectofletes_isw_1313.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ReservacionViewModelFactory(
    private val repository: IReservacionRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReservacionViewModel(repository) as T
    }

}