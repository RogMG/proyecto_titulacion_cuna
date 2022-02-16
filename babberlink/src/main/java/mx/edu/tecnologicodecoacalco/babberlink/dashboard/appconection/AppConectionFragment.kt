package mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection

import android.app.AlertDialog
import android.app.SearchManager
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.google.zxing.integration.android.IntentIntegrator
import mx.edu.tecnologicodecoacalco.babberlink.databinding.FragmentAppconnectionBinding
import mx.edu.tecnologicodecoacalco.babberlink.utils.GenericDialog

class AppConectionFragment : Fragment() {

    private var _binding: FragmentAppconnectionBinding? = null

    private val binding get() = _binding!!

    private val dialog by lazy{
        GenericDialog()
    }
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
          binding.buttonScanQr.setOnClickListener {
              val intentIntegrator = IntentIntegrator.forSupportFragment(this@AppConectionFragment)
              intentIntegrator.setDesiredBarcodeFormats(listOf(IntentIntegrator.QR_CODE))
              intentIntegrator.initiateScan()

          }

        return view
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(resultCode, data)
        Log.d("APPCONECTIONFRAGMENT", "result ${ data }")

        if (result != null) {
            Log.d("APPCONECTIONFRAGMENT", "Te gustaria suscribirte a: ${ result.contents }")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}