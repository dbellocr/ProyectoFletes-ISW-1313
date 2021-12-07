package com.isw.proyectofletes_isw_1313.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.isw.proyectofletes_isw_1313.R
import com.isw.proyectofletes_isw_1313.adapter.ReservacionAdapter
import com.isw.proyectofletes_isw_1313.databinding.FragmentGalleryBinding
import com.isw.proyectofletes_isw_1313.model.Reservacion
import com.isw.proyectofletes_isw_1313.repository.IReservacionRepository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root


        IReservacionRepository().getReservaciones().enqueue(object : Callback<List<Reservacion>> {
            override fun onResponse(
                call: Call<List<Reservacion>>,
                response: Response<List<Reservacion>>
            ) {
                val reservaciones = response.body()

                reservaciones?.let {
                    mostrarReservaciones(it)
                }
            }

            override fun onFailure(call: Call<List<Reservacion>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }
        })

        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_nav_gallery_to_nav_add_Reservacion)
        }
        return root
    }
    private fun mostrarReservaciones(reservacion: List<Reservacion>)
    {
        binding.reciclador.layoutManager = LinearLayoutManager(context)
        binding.reciclador.adapter = ReservacionAdapter(reservacion)
    }

}