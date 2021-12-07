package com.isw.proyectofletes_isw_1313

import android.os.AsyncTask
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
import org.json.JSONObject
import java.io.DataOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

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
        val fechaActual= SimpleDateFormat("dd-MM-yyyy").format(Date())
        binding.etFechaSolicitud.setText(fechaActual)
        binding.etVehiculo.setText(/*args.vehiculoActual.placa.toString()*/"CL-164789")

        binding.btAgregar.setOnClickListener { EnviarReservacionPost() /*agregarReservacion()*/ }
            return root
    }

    private fun agregarReservacion() {
            val fechaSolicitud = binding.etFechaSolicitud.text.toString()
            val fechaServicio = binding.etFechaServicio.text.toString()
            val materialOtro = binding.etMaterialOtro.text.toString()
            val vehiculo = binding.etVehiculo.text.toString()
            if (validos(fechaSolicitud, fechaServicio, materialOtro, vehiculo)) {
                val fechaActual= SimpleDateFormat("yyyy-MM-dd").format(Date())
                val fechaSolicitud = fechaActual
                val fechaServicio = binding.etFechaServicio.text.toString()
                val materialOtro = binding.etMaterialOtro.text.toString()
                val vehiculo = binding.etVehiculo.text.toString()
                var usuario= usuarioLog.toString()
                if (usuario.equals("")){
                    usuario="erickrvrv1717@gmail.com"
                }
                val myPost = Reservacion(0,fechaSolicitud, fechaServicio, materialOtro, vehiculo, usuario)
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

    inner class EnviarReservacionPost: AsyncTask<Reservacion, Void?, String>() {
        override fun doInBackground(vararg p0: Reservacion): String {
            var respuesta = ""
            val conn: HttpURLConnection
            conn = URL("http://10.0.2.2:4478/api/reservacion").openConnection() as HttpURLConnection
            conn.setRequestProperty("Content-type", "application/json; charset=UTF-8")

            val dato = JSONObject()
            dato.put("fechaSolicitud", p0[0].getFechaSolicitud())
            dato.put("fechaServicio", p0[0].getFechaServicio())
            dato.put("material", p0[0].getMaterial())
            dato.put("idVehiculo", p0[0].getIdVehiculo())
            dato.put("usuarioSolicitante", p0[0].getUsuarioSolicitante())

            conn.requestMethod = "POST"
            conn.doInput = true
            conn.doOutput = true
            conn.connect()

            val envio = DataOutputStream(conn.outputStream)
            envio.writeBytes(dato.toString())
            envio.flush()
            envio.close()
            Toast.makeText(requireContext(), getString(R.string.reservacionRealizada), Toast.LENGTH_SHORT)
            return (conn.responseCode).toString()
        }
    }

}