package com.isw.proyectofletes_isw_1313.data

import com.isw.proyectofletes_isw_1313.model.Vehiculo
import com.isw.proyectofletes_isw_1313.repository.IVehiculoRepository


class VehiculoReader() {
        fun read(): List<Vehiculo> {
            return IVehiculoRepository().getCamiones().execute().body()!!
        }
}