package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.appconection.presentation.view

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.appconection.presentation.viewmodel.AppConnectionFragmentViewModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.FragmentAppConectionBinding

class AppConnectionFragment : Fragment() {

    private var _binding: FragmentAppConectionBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val appConnectionFragmentViewModel: AppConnectionFragmentViewModel by viewModels()

    private var userEmail = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppConectionBinding.inflate(inflater, container, false)
        val view = binding.root
        Firebase.auth.currentUser?.email?.let { userEmail = it }

        appConnectionFragmentViewModel.getUserData(userEmail)

        appConnectionFragmentViewModel.userDataResponse.observe(requireActivity(), {
            val bitmap = generateQRCode(it.userToken)
            binding.qrGeneratorTextView.setImageBitmap(bitmap)
        })

        return view
    }

    private fun generateQRCode(text: String): Bitmap {
        val width = 300
        val height = 300
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val codeWriter = MultiFormatWriter()
        try {
            val bitMatrix = codeWriter.encode(text, BarcodeFormat.QR_CODE, width, height)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        } catch (e: WriterException) { Log.d("APPCONECTIONFRAGMENT", "generateQRCode: ${e.message}") }
        return bitmap
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}