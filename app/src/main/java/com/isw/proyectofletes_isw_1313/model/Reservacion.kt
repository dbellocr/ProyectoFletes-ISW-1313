package com.isw.proyectofletes_isw_1313.model


data class Reservacion  (
    private var id: Int,
    private var fechaSolicitud: String,
    private var fechaServicio: String,
    private var material: String,
    private var idVehiculo: String,
    private var usuarioSolicitante: String){
    fun getId(): Int{
        return id
    }
    fun getFechaSolicitud(): String{
        return fechaSolicitud
    }
    fun getFechaServicio(): String{
        return fechaServicio
    }
    fun getMaterial(): String{
        return material
    }
    fun getIdVehiculo(): String{
        return idVehiculo
    }
    fun getUsuarioSolicitante(): String{
        return usuarioSolicitante
    }

    override fun toString(): String {
        return "Reservacion(id=$id, fechaSolicitud='$fechaSolicitud', fechaServicio='$fechaServicio', material='$material', idVehiculo='$idVehiculo', usuarioSolicitante='$usuarioSolicitante')"
    }

}