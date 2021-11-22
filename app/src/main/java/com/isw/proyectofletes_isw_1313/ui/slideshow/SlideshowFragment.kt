package com.isw.proyectofletes_isw_1313.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.isw.proyectofletes_isw_1313.R
import com.isw.proyectofletes_isw_1313.data.VehiculoReader
import com.isw.proyectofletes_isw_1313.databinding.FragmentSlideshowBinding
import com.isw.proyectofletes_isw_1313.model.Vehiculo
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.isw.proyectofletes_isw_1313.helpers.BitmapHelper
import com.isw.proyectofletes_isw_1313.adapter.MarcadorAdapter


class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel
    private var _binding: FragmentSlideshowBinding? = null
    private lateinit var googleMap: GoogleMap

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val vehiculoIcon: BitmapDescriptor by lazy {
        val color = ContextCompat.getColor(requireContext(), R.color.black)
        BitmapHelper.vectorToBitmap(requireContext(), R.drawable.ic_car, color)
    }
    private lateinit var vehiculos : List<Vehiculo>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        vehiculos = VehiculoReader().read()

        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment

        mapFragment?.getMapAsync { googleMap ->
            // addMarkers(googleMap)

            mapFragment?.getMapAsync { googleMap ->
                addMarkers(googleMap)

                // Set custom info window adapter.
                googleMap.setInfoWindowAdapter(MarcadorAdapter(requireContext()))
            }

            googleMap.setInfoWindowAdapter(MarcadorAdapter(requireContext()))

            mapFragment?.getMapAsync { googleMap ->
                // Ensure all places are visible in the map.
                googleMap.setOnMapLoadedCallback {
                    val bounds = LatLngBounds.builder()
                    vehiculos.forEach { bounds.include(it.position) }
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 20))
                }
            }
        }

        return root
    }

    private fun addMarkers(googleMap: GoogleMap) {
        vehiculos.forEach { place ->
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .title(place.id)
                    .position(LatLng(place.latitud.toDouble(), place.longitud.toDouble()))
                    .icon(vehiculoIcon)
            )
            marker.tag = place
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Para permitir llamadas síncronas
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}