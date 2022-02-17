package mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.presentation.view

import android.app.AlertDialog
import android.app.SearchManager
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.zxing.integration.android.IntentIntegrator
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.presentation.viewmodel.AppConnectionFragmentViewModel
import mx.edu.tecnologicodecoacalco.babberlink.databinding.FragmentAppconnectionBinding
import mx.edu.tecnologicodecoacalco.babberlink.utils.GenericDialog

class AppConectionFragment : Fragment() {

    private var _binding: FragmentAppconnectionBinding? = null

    private val binding get() = _binding!!

    private val dialog by lazy{
        GenericDialog()
    }
    private var userEmail = ""

    private val appConnectionFragmentViewModel: AppConnectionFragmentViewModel by viewModels()

    private lateinit var userToken: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            dialog.dialogBuilderCloseSession(requireContext()) { requireActivity().finish() }.show()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppconnectionBinding.inflate(inflater, container, false)
        val view = binding.root
        Firebase.auth.currentUser?.email?.let { userEmail = it }
        binding.buttonScanQr.setOnClickListener {
              val intentIntegrator = IntentIntegrator.forSupportFragment(this@AppConectionFragment)
              intentIntegrator.setDesiredBarcodeFormats(listOf(IntentIntegrator.QR_CODE))
              intentIntegrator.initiateScan()
          }
        appConnectionFragmentViewModel.tokenAddedResponse.observe(requireActivity(), {
            if(it){
                subscribeToTopic(userToken)
            }
        })
        appConnectionFragmentViewModel.userDataResponse.observe(requireActivity(), {
            if(it.isEmpty()){
                Toast.makeText(requireContext(), "No pudimos encontrar en token", Toast.LENGTH_SHORT).show()
            }else{
                AlertDialog.Builder(requireContext())
                    .setTitle("¿Quieres vincularte tu dispositivo?")
                    .setMessage("""
                         Su nombre es: ${it.first().nombre}
                         Su número de emergencia es: ${it.first().celular}
                         Su correo electronico es: ${it.first().correo}
                    """.trimIndent())
                    .setPositiveButton("Aceptar"){ i, d ->
                        appConnectionFragmentViewModel.addUserTokenToData(userEmail, userToken )
                    }.setNegativeButton("Denegar"){ i, d ->
                        i.dismiss()
                    }.create().show()
            }
        })
        return view
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(resultCode, data)
        if (result != null) {
            userToken = result.contents
            appConnectionFragmentViewModel.getUserData(userToken)
        }
    }

    fun subscribeToTopic(token: String){
        FirebaseMessaging.getInstance().subscribeToTopic(token)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Notificaciones activadas con exito", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Algo salío mal", Toast.LENGTH_SHORT ).show()
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}