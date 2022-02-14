package mx.edu.tecnologicodecoacalco.proyecto_titulacion.utils

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import com.google.firebase.auth.FirebaseAuth

class GenericDialog {

    fun dialogBuilderCloseSession(context: Context, finisher:() -> Unit): Dialog {
        return AlertDialog.Builder(context)
            .setTitle("¿Quieres cerrar sesion?")
            .setMessage("Cerrar la sesión en este momento te devolverá a el Login")
            .setNegativeButton("Cerrar Sesión") { i, e ->
                finisher()
                FirebaseAuth.getInstance().signOut()
                i.dismiss()
            }
            .setPositiveButton("Cerrar dialogo") {i,e ->
                i.dismiss()
            }.create()
    }

}