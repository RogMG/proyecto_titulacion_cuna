package mx.edu.tecnologicodecoacalco.babberlink.utils

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import mx.edu.tecnologicodecoacalco.babberlink.login.presentation.view.LoginActivity
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat


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

    fun dialogBuilderGetSession(context: Context): Dialog {
        return AlertDialog.Builder(context)
            .setTitle("Inicia sesión porfavor")
            .setMessage("Inicia sesión para ver el estado de tu bebé")
            .setPositiveButton("Ir a el login") {i,e ->
                LoginActivity.launch(context)
                i.dismiss()
            }.create()
    }

    fun dialogCall(context: Context, number: Int): Dialog {
        return AlertDialog.Builder(context)
            .setTitle("ALERTA!")
            .setMessage("Llama a tu número de emergencia en este momento!")
            .setPositiveButton("LLAMAR") {i,e ->
                startCall(number,context)
            }.create()
    }

    fun startCall(number: Int, context: Context){
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$number") //change the number
        context.startActivity(callIntent)
    }

}