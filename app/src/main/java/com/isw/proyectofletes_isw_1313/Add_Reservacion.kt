package com.isw.proyectofletes_isw_1313

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.isw.proyectofletes_isw_1313.MainActivity.Companion.usuarioLog
import com.isw.proyectofletes_isw_1313.databinding.FragmentAddReservacionBinding
import com.isw.proyectofletes_isw_1313.model.Reservacion
import com.isw.proyectofletes_isw_1313.repository.IReservacionRepository
import com.isw.proyectofletes_isw_1313.repository.ReservacionViewModel
import com.isw.proyectofletes_isw_1313.repository.ReservacionViewModelFactory

class Add_Reservacion : Fragment() {
    private var _binding: FragmentAddReservacionBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel:ReservacionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //private val args by navArgs<Add_ReservacionArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddReservacionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val viewModelFactory = ReservacionViewModelFactory(IReservacionRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(ReservacionViewModel::class.java)

        //Fecha del dia autom (Cambiar)
        binding.etFechaServicio.setText("2021-12-07")
        binding.etVehiculo.setText(/*args.vehiculoActual.placa.toString()*/"000")

        binding.btAgregar.setOnClickListener { agregarReservacion() }
            return root
    }

    private fun agregarReservacion() {
            val fechaSolicitud = binding.etFechaSolicitud.text.toString()
            val fechaServicio = binding.etFechaServicio.text.toString()
            val materialOtro = binding.etMaterialOtro.text.toString()
            val vehiculo = binding.etVehiculo.text.toString()
            if (validos(fechaSolicitud, fechaServicio, materialOtro, vehiculo)) {

                val fechaSolicitud = binding.etFechaSolicitud.text.toString()
                val fechaServicio = binding.etFechaServicio.text.toString()
                val materialOtro = binding.etMaterialOtro.text.toString()
                val vehiculo = binding.etVehiculo.text.toString()
                val myPost = Reservacion(0,fechaSolicitud, fechaServicio, materialOtro, vehiculo, usuarioLog.toString())
                viewModel.agregarReservacion(myPost)
                /*viewModel.myResponse.observe(this, Observer { response ->
                    if (response.isSuccessful) {
                        Log.d("Main", response.body().toString())
                        Log.d("Main", response.code().toString())
                        Log.d("Main", response.message())
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.reservacionRealizada),
                            Toast.LENGTH_SHORT
                        ).show()
                        findNavController().navigate(R.id.action_nav_add_Reservacion_to_nav_gallery)
                    )*/
            } else {
                    Toast.makeText(requireContext(), getString(R.string.faltaInfo), Toast.LENGTH_SHORT).show()
            }
    }

    private fun validos(fechaSolicitud: String, fechaServicio: String, materialOtro: String, vehiculo: String): Boolean {
        return !(fechaSolicitud.isEmpty() || fechaServicio.isEmpty() || materialOtro.isEmpty() || vehiculo.isEmpty())
    }
}