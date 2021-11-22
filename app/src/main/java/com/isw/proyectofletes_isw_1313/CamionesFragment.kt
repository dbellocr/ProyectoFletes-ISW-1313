package com.isw.proyectofletes_isw_1313

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.PackageManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.isw.proyectofletes_isw_1313.adapter.VehiculoAdapter
import com.isw.proyectofletes_isw_1313.databinding.FragmentCamionesBinding
import com.isw.proyectofletes_isw_1313.model.Vehiculo
import com.isw.proyectofletes_isw_1313.repository.IVehiculoRepository
import retrofit2.Call
import retrofit2.Response
import androidx.core.content.PackageManagerCompat.LOG_TAG
import retrofit2.Callback


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
//KOTLIN
class CamionesFragment : Fragment() {
    private var _binding: FragmentCamionesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCamionesBinding.inflate(inflater, container, false)
        val root: View = binding.root


        IVehiculoRepository().getCamiones().enqueue(object : Callback<List<Vehiculo>> {
            override fun onResponse(
                call: Call<List<Vehiculo>>,
                response: Response<List<Vehiculo>>
            ) {
                val vehiculos = response.body()

                vehiculos?.let {
                    mostrarCamiones(it)
                }
            }

            override fun onFailure(call: Call<List<Vehiculo>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }
        })

        return root
    }
    private fun mostrarCamiones(vehiculo: List<Vehiculo>)
    {
        binding.reciclador.layoutManager = LinearLayoutManager(context)
        binding.reciclador.adapter = VehiculoAdapter(vehiculo)
    }

}