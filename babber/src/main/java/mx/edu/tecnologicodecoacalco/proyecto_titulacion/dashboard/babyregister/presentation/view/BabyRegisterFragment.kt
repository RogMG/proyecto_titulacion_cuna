package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.babyregister.presentation.view

import android.R.attr
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.babyregister.presentation.viewmodel.BabyRegisterFragmentViewModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.FragmentBabyRegisterBinding
import android.content.Intent
import android.widget.Toast
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.FileNotFoundException
import java.io.InputStream
import android.provider.MediaStore

import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.R


class BabyRegisterFragment : Fragment() {

    companion object {
        private const val IMAGE_PICKER_CODE = 422
    }

    private var _binding: FragmentBabyRegisterBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var imageAvatar: Bitmap

    private val hardwareCode: String by lazy {
        ""
    }

    private val babyRegisterFragmentViewModel: BabyRegisterFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBabyRegisterBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.imageViewBaby.setOnClickListener {
            getImageFromGallery()
        }
        binding.registerButtonBaby.setOnClickListener {
            registerBaby()
        }

        babyRegisterFragmentViewModel.registerBabyResponse.observe(requireActivity(), {
            if (it){
                Toast.makeText(requireContext(), "Ya se registro", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(requireContext(), "Algo salío mal", Toast.LENGTH_LONG).show()

            }
        })

        return view
    }

    fun getImageFromGallery(){
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, IMAGE_PICKER_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode ==  Activity.RESULT_OK) {
            try {
                val imageUri: Uri = data?.data ?: Uri.EMPTY
                imageAvatar= MediaStore.Images.Media.getBitmap(requireContext().contentResolver, imageUri)
                binding.imageViewBaby.setImageBitmap(imageAvatar)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                Toast.makeText(requireContext(), "Algo salío mal", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(requireContext(), "No escogiste ninguna imagen", Toast.LENGTH_LONG).show()
        }
    }

    fun registerBaby(){
        if(true){
//            babyRegisterFragmentViewModel.registerBaby(
//                "rogelio@example.com",
//                nombre = binding.editTextBabyName.text.toString(),
//                apellidoPaterno = binding.editTextBabyPatern.text.toString(),
//                apellidoMaterno = binding.editTextBabyMatern.text.toString(),
//                edad = binding.editTextEdadBaby.text.toString(),
//                peso = binding.editTextBabayWight.text.toString(),
//                sexo = binding.editTextBabySex.text.toString(),
//                hardwareVinculado = hardwareCode
//            )
            babyRegisterFragmentViewModel.registerBaby(
                "rogelio@example.com",
                nombre = "Rogelio",
                apellidoPaterno = "Martinez",
                apellidoMaterno = "Gonzalez",
                edad = "15",
                peso = "1.6",
                sexo = "Masculino",
                hardwareVinculado = hardwareCode
            )
        }else{
            Toast.makeText(requireContext(), "Porfavor llena todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    fun areFieldsValid(): Boolean{
        if(
            binding.editTextBabayWight.text.isNotEmpty() ||
            binding.editTextBabyPatern.text.isNotEmpty() ||
            binding.editTextBabyMatern.text.isNotEmpty() ||
            binding.editTextBabySex.text.isNotEmpty() ||
            binding.editTextBabayWight.text.isNotEmpty() ||
            binding.editTextEdadBaby.text.isNotEmpty()
        ){
            return true
        }
        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}