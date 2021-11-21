package com.isw.proyectofletes_isw_1313.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.isw.proyectofletes_isw_1313.R
import com.isw.proyectofletes_isw_1313.databinding.CamionFilaBinding
import com.isw.proyectofletes_isw_1313.model.Vehiculo

class VehiculoAdapter(val vehiculos: List<Vehiculo>): RecyclerView.Adapter<VehiculoAdapter.VehiculoViewHolder>() {

    class  VehiculoViewHolder(val view: CamionFilaBinding): RecyclerView.ViewHolder(view.root){
        fun bind(vehiculo: Vehiculo){
            view.tvPlaca.text = vehiculo.id.toString()
            view.tvConductor.text = "Conducido por: " + vehiculo.conductor
            view.tvModelo.text = vehiculo.modelo
            Glide.with(view.root).load(vehiculo.urlFotografía).into(view.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiculoViewHolder {
        val itemBinding = CamionFilaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VehiculoViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: VehiculoViewHolder, position: Int) {
        val vehiculo = vehiculos[position]
        holder.bind(vehiculo)
    }

    override fun getItemCount(): Int {
       return vehiculos.size
    }
}