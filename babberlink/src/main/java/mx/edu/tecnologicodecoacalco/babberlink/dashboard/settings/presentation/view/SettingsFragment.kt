package mx.edu.tecnologicodecoacalco.babberlink.dashboard.settings.presentation.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.settings.domain.model.UserDataModel
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.settings.presentation.viewmodel.SettingsFragmentViewModel
import mx.edu.tecnologicodecoacalco.babberlink.databinding.FragmentSettingsBinding
import mx.edu.tecnologicodecoacalco.babberlink.utils.GenericDialog
import java.io.FileNotFoundException

class SettingsFragment : Fragment() {

    companion object {
        private const val USER_IMAGE_PICKER_CODE = 522
    }

    private var _binding: FragmentSettingsBinding? = null

    private val binding get() = _binding!!

    private lateinit var name: EditText
    private lateinit var fatherLastName: EditText
    private lateinit var momLastName: EditText
    private lateinit var phone: EditText
    private lateinit var profileImage: ImageView

    private val settingsFragmenViewModel: SettingsFragmentViewModel by viewModels()

    private var email: String = ""

    private var imageUri: Uri = Uri.EMPTY

    private var imageId: String = ""

    private var hasChangedProfileImage = false

    private lateinit var data: UserDataModel

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
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val view = binding.root
        Firebase.auth.currentUser?.email?.let { email = it }
        profileImage = binding.profileSettingsImageView
        name = binding.nameSettingsEditText
        fatherLastName = binding.fatherLastSettingsEditText
        momLastName = binding.momLastSettingsEditText
        phone = binding.phoneSettingsEditText

        binding.saveSettingsButton.setOnClickListener {
            compareChanges()
        }
        binding.helpSettingsButton.setOnClickListener {

        }
        binding.closeSessionSettingsButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            requireActivity().finish()

        }

        settingsFragmenViewModel.getUserData(email)

        settingsFragmenViewModel.userDataResponse.observe(requireActivity(), {
            data = it
            displayUserData()
        })

        settingsFragmenViewModel.userImageResponse.observe(requireActivity(), {
            Picasso.get().load(it).into(profileImage)
        })

        settingsFragmenViewModel.saveBabyUserImageId.observe(requireActivity(), {
            imageId = it
        })

        settingsFragmenViewModel.babyUserImageId.observe(requireActivity(), {
            imageId = it
            sendDataToServer()
        })

        settingsFragmenViewModel.notificationResponse.observe(requireActivity(), {
            if(it){
                Toast.makeText(requireContext(), "Operacion Exitosa", Toast.LENGTH_SHORT).show()
            }
        })
        settingsFragmenViewModel.deletedUserImage.observe(requireActivity(), {
            if(it){
                settingsFragmenViewModel.saveBabyPhoto(imageUri)
            }else{
                Toast.makeText(requireContext(), "No pudimos borrar la imagen del server", Toast.LENGTH_SHORT).show()
            }
        })

        profileImage.setOnClickListener {
            getImageFromGallery()
        }


        return view
    }


    private fun getImageFromGallery(){
        val photoPickerIntent = Intent(Intent.ACTION_GET_CONTENT)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, USER_IMAGE_PICKER_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode ==  Activity.RESULT_OK && requestCode == USER_IMAGE_PICKER_CODE) {
            try {
                imageUri = data?.data  ?: Uri.EMPTY
                val imageAvatar= MediaStore.Images.Media.getBitmap(requireContext().contentResolver, imageUri)
                profileImage.setImageBitmap(imageAvatar)
                hasChangedProfileImage = true
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                Toast.makeText(requireContext(), "Algo salío mal", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(requireContext(), "No escogiste ninguna imagen", Toast.LENGTH_LONG).show()
        }
    }

    private fun displayUserData(){
        name.setText(data.nombre)
        fatherLastName.setText(data.apellidoPaterno)
        momLastName.setText(data.apellidoMaterno)
        phone.setText(data.celular)
        if(data.imageId.isNotEmpty()){
            settingsFragmenViewModel.getUserImage(data.imageId)
        }
    }

    private fun compareChanges(){
        if(hasChangedProfileImage && data.imageId.isNotEmpty()){
            settingsFragmenViewModel.setDeleteUserImage(imageId)
        }else if(hasChangedProfileImage && data.imageId.isEmpty()){
            settingsFragmenViewModel.saveBabyPhoto(imageUri)
        }else {
            sendDataToServer()
        }
    }


    private fun sendDataToServer(){
        val dataMap = hashMapOf<String, Any>()
        if(name.text.toString() != data.nombre){
            dataMap["nombre"] = name.text.toString()
        }
        if(fatherLastName.text.toString() != data.nombre){
            dataMap["apellidoPaterno"] = fatherLastName.text.toString()
        }
        if(momLastName.text.toString() != data.nombre){
            dataMap["apellidoMaterno"] = momLastName.text.toString()
        }
        if(phone.text.toString() != data.nombre){
            dataMap["celular"] = phone.text.toString()
        }
        if(imageId != data.imageId){
            dataMap["imageId"] = imageId
        }
        settingsFragmenViewModel.setUserDataUpdate(email, dataMap)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

