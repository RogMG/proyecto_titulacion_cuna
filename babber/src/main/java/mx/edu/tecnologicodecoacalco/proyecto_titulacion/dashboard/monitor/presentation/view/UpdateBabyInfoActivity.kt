package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.presentation.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.activity.viewModels
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.model.BabyDTO
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.model.BabyIdModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.presentation.viewmodel.UpdateBabyInfoActivityViewModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.ActivityUpdateBabyInfoBinding
import java.io.FileNotFoundException
import java.io.Serializable

class UpdateBabyInfoActivity : AppCompatActivity() {

    companion object{

        private const val MODEL_KEY = "modelKey"
        private const val MODEL_ID_KEY = "modelIdKey"
        private const val BABY_IMAGE_CODE = 877

        fun launch(context: Context, model: BabyDTO, babyID: String){
            val intent = Intent(context, UpdateBabyInfoActivity::class.java)
            intent.putExtra(MODEL_KEY, model as Serializable)
            intent.putExtra(MODEL_ID_KEY, babyID)
            context.startActivity(intent)
        }

    }

    private lateinit var image: ImageView
    private lateinit var name: TextView
    private lateinit var momLastName: TextView
    private lateinit var dadLastName: TextView
    private lateinit var age: EditText
    private lateinit var sex: TextView
    private lateinit var weight: EditText
    private lateinit var saveButton: Button

    private val updateBabyInfoActivityViewModel: UpdateBabyInfoActivityViewModel by viewModels()

    private val binding by lazy {
        ActivityUpdateBabyInfoBinding.inflate(layoutInflater)
    }

    private var imageUri = Uri.EMPTY

    private var email: String = ""

    private var hasChangedProfileImage = false

    private var imageId: String = ""

    private lateinit var dataReceived: BabyDTO

    private lateinit var idReceived: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val extra = intent.extras
        Firebase.auth.currentUser?.email?.let { email = it }
        dataReceived = extra?.getSerializable(MODEL_KEY) as BabyDTO
        idReceived = extra.getString(MODEL_ID_KEY) as String
        image = binding.profileUpdateMonitorImageView
        name = binding.nameUpdateMonitorTextView
        momLastName = binding.momUpdateMonitorTextView
        dadLastName = binding.fatherUpdateMonitorTextView
        sex = binding.sexUpdateMonitorTextView
        age = binding.ageUpdateMonitorTextView
        weight = binding.weightUpdateMonitorTextView
        saveButton = binding.saveUpdateMonitorButton

        setUserData()

        image.setOnClickListener {
            getImageFromGallery()
        }

        saveButton.setOnClickListener {
            compareChanges()
        }

        updateBabyInfoActivityViewModel.imageFromStorageResponse.observe(this, {
            Picasso.get().load(it).into(image)
        })

        updateBabyInfoActivityViewModel.setBabyUserImageId.observe(this, {
            imageId = it
        })

        updateBabyInfoActivityViewModel.setSaveBabyUserImageId.observe(this, {
            imageId = it
            sendDataToServer()
        })

        updateBabyInfoActivityViewModel.deleteBabyImageResponse.observe(this, {
            if(it){
                updateBabyInfoActivityViewModel.saveImageBaby(imageUri)
            }else{
                Toast.makeText(this, "No pudimos borrar la imagen del server", Toast.LENGTH_SHORT).show()
            }

        })

        updateBabyInfoActivityViewModel.notificationResponse.observe(this, {
            if(it){
                Toast.makeText(this, "Operacion exitosa", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Algo salío mal, intentelo más tarde porfavor", Toast.LENGTH_SHORT).show()
            }
        })


    }

    private fun setUserData(){
        name.text = dataReceived.nombre
        momLastName.text = dataReceived.apellidoMaterno
        dadLastName.text = dataReceived.apellidoPaterno
        sex.text = dataReceived.sexo
        age.setText(dataReceived.edad)
        weight.setText(dataReceived.peso)
        if(dataReceived.imageId.isNotEmpty()){
            updateBabyInfoActivityViewModel.getImageFromStorage(dataReceived.imageId)
        }
    }

    private fun compareChanges(){
        if(hasChangedProfileImage && dataReceived.imageId.isNotEmpty()){
            updateBabyInfoActivityViewModel.setDeleteBabyImage(dataReceived.imageId)
        }else if (hasChangedProfileImage && dataReceived.imageId.isEmpty()){
            updateBabyInfoActivityViewModel.saveImageBaby(imageUri)
        }
        else{
            sendDataToServer()
        }
    }

    private fun sendDataToServer(){
        val dataMap = hashMapOf<String, Any>()
        dataMap["apellidoMaterno"] = momLastName.text.toString()
        dataMap["apellidoPaterno"] = dadLastName.text.toString()
        dataMap["edad"] = age.text.toString()
        dataMap["nombre"] = name.text.toString()
        dataMap["imageId"] = imageId
        dataMap["peso"] = weight.text.toString()
        dataMap["sexo"] = sex.text.toString()
        dataReceived.imageId = imageId
        updateBabyInfoActivityViewModel.setUpdateBabyInfo(
            email,
            idReceived,
            dataMap
        )
    }

    private fun getImageFromGallery(){
        val photoPickerIntent = Intent(Intent.ACTION_GET_CONTENT)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, BABY_IMAGE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode ==  Activity.RESULT_OK && requestCode == BABY_IMAGE_CODE) {
            try {
                imageUri = data?.data  ?: Uri.EMPTY
                val imageAvatar= MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
                image.setImageBitmap(imageAvatar)
                hasChangedProfileImage = true
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                Toast.makeText(this, "Algo salío mal", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "No escogiste ninguna imagen", Toast.LENGTH_LONG).show()
        }
    }



}
