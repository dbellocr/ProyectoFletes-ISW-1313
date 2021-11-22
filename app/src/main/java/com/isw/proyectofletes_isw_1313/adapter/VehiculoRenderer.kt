package com.isw.proyectofletes_isw_1313.adapter

import android.content.Context
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.isw.proyectofletes_isw_1313.helpers.BitmapHelper
import com.isw.proyectofletes_isw_1313.R
import com.isw.proyectofletes_isw_1313.model.Vehiculo

class VehiculoRenderer(  private val context: Context,
                         map: GoogleMap,
                         clusterManager: ClusterManager<Vehiculo>
): DefaultClusterRenderer<Vehiculo>(context, map, clusterManager) {

    /**
     * The icon to use for each cluster item
     */
    private val bicycleIcon: BitmapDescriptor by lazy {
        val color = ContextCompat.getColor(context,
            R.color.colorPrimary
        )
        BitmapHelper.vectorToBitmap(
            context,
            R.drawable.ic_car,
            color
        )
    }

    /**
     * Method called before the cluster item (the marker) is rendered.
     * This is where marker options should be set.
     */
    override fun onBeforeClusterItemRendered(
        item: Vehiculo,
        markerOptions: MarkerOptions
    ) {
        markerOptions.title(item.id)
            .position(LatLng(item.latitud.toDouble(), item.longitud.toDouble()))
            .icon(bicycleIcon)
    }

    /**
     * Method called right after the cluster item (the marker) is rendered.
     * This is where properties for the Marker object should be set.
     */
    override fun onClusterItemRendered(clusterItem: Vehiculo, marker: Marker) {
        marker.tag = clusterItem
    }
}