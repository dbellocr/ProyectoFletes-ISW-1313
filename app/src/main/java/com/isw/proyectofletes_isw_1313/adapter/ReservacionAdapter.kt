package com.isw.proyectofletes_isw_1313.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.isw.proyectofletes_isw_1313.databinding.ReservacionFilaBinding
import com.isw.proyectofletes_isw_1313.model.Reservacion

class ReservacionAdapter(val reservaciones: List<Reservacion>): RecyclerView.Adapter<ReservacionAdapter.ReservacionViewHolder>() {

    class ReservacionViewHolder(val view: ReservacionFilaBinding): RecyclerView.ViewHolder(view.root){
        fun bind(reservacion: Reservacion){
            view.tvId.text = reservacion.getId().toString()
            var fechaN = reservacion.getFechaSolicitud().toString()
            var anno= fechaN.substring(0,4).toString()
            var mes= fechaN.substring(5,7).toString()
            var dia= fechaN.substring(8,10).toString()
            view.tvFecha1.text =dia.toString() + "/" + mes.toString() + "/" + anno
            var fechaN2 = reservacion.getFechaServicio().toString()
            var anno2= fechaN2.substring(0,4).toString()
            var mes2= fechaN2.substring(5,7).toString()
            var dia2= fechaN2.substring(8,10).toString()
            view.tvFecha2.text =dia2.toString() + "/" + mes2.toString() + "/" + anno2
            view.tvVehiculo.text = reservacion.getIdVehiculo().toString()
            view.tvMaterial.text = reservacion.getMaterial().toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservacionViewHolder {
        val itemBinding = ReservacionFilaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReservacionViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ReservacionViewHolder, position: Int) {
        val reservacion = reservaciones[position]
        holder.bind(reservacion)
    }

    override fun getItemCount(): Int {
        return reservaciones.size
    }
}