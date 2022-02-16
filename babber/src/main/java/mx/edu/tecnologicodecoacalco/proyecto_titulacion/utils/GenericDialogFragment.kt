package mx.edu.tecnologicodecoacalco.proyecto_titulacion.utils

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.R

import android.media.RingtoneManager

import android.media.Ringtone
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.presentation.view.LoginActivity


class GenericDialogFragment: DialogFragment() {

    private var title: String? = null
    private var message: String? = null
    private var positiveButton: String? = null
    private var negativeButton: String? = null
    private var hasCancelableCondition = false
    private var number:String?  = null

    private lateinit var r: Ringtone

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            message = it.getString(ARG_MESSAGE)
            positiveButton = it.getString(ARG_POSITIVE)
            negativeButton = it.getString(ARG_NEGATIVE)
            hasCancelableCondition = it.getBoolean(ARG_CANCELABLE)
            number = it.getString(ARG_NUMBER)

        }
        vibrate()
        playRingtone()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        r.stop()
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButton) { _,_ ->
                checkPermission()
            }
            .setNegativeButton(negativeButton) { _,_ -> this.dismiss() }
            .setCancelable(hasCancelableCondition)
            .create()

    }

    fun vibrate(){
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createWaveform(pattern, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(1000)
        }
    }

    fun playRingtone(){
        val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        r = RingtoneManager.getRingtone(requireContext().applicationContext,
            notification)
        r.play()
    }

    fun checkPermission(){
        if (ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_DENIED){
            Toast.makeText(requireContext(), "No podemos hacer la llamada porque no haz concedido permisos, hazlo manualmente", Toast.LENGTH_LONG).show()
        }else{
            startCall(requireContext(), number)
        }
    }

    fun startCall(context: Context,number: String? = ""){
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$number")
        context.startActivity(callIntent)
    }


        companion object {
            const val TAG = "GenericDialog"
            private const val ARG_TITLE = "titleArg"
            private const val ARG_MESSAGE = "titleMessage"
            private const val ARG_POSITIVE = "titlePositive"
            private const val ARG_NEGATIVE = "titleNegative"
            private const val ARG_CANCELABLE = "Cancelable"
            private const val ARG_NUMBER = "phoneNumber"

            private val pattern = longArrayOf( 0, 1000, 1000, 1000, 1000, 1000)

            @JvmStatic
            fun newInstance(
                title: String = "",
                message: String = "",
                positiveButton: String = "",
                negativeButton: String = "",
                hasCancelableCondition: Boolean = false,
                number: String
            ) =
                GenericDialogFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_TITLE, title)
                        putString(ARG_MESSAGE, message)
                        putString(ARG_POSITIVE, positiveButton)
                        putString(ARG_NEGATIVE, negativeButton)
                        putBoolean(ARG_CANCELABLE, hasCancelableCondition)
                        putString(ARG_NUMBER, number)
                    }
                }
        }

}
