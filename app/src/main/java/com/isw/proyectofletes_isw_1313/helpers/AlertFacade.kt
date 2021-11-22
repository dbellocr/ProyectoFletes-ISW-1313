package com.isw.proyectofletes_isw_1313.helpers

import android.app.AlertDialog
import android.content.Context

class AlertFacade {
    companion object{

        fun crearAlertaOk(titulo: CharSequence, mensaje: CharSequence, context : Context){
            val builder = AlertDialog.Builder(context)
            builder.setTitle(titulo)
            builder.setMessage(mensaje)

            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            val b = builder.create()
            b.show()
        }
        /*
            Se encarga de crear una alerta de confirmacion. Si el usuario selecciona si entonces se ejecuta la funcion que es pasada como parametro
         */
        fun crearAlertaConfirmacion(titulo: CharSequence, mensaje: CharSequence, context : Context, function: () -> (Unit)) {
            val builder = AlertDialog.Builder(context)

            builder.setTitle(titulo)
            builder.setMessage(mensaje)

            builder.setPositiveButton("Si") { dialog, _ ->
                function()
                dialog.dismiss()
            }
            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            val b = builder.create()
            b.show()
        }
    }
}