package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.babyregister.presentation.viewmodel

import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.babyregister.domain.usecase.SetRegisterBabyUseCase
import java.util.*

class BabyRegisterFragmentViewModel: ViewModel() {

    private val babyRegisterUseCase by lazy {
        SetRegisterBabyUseCase()
    }

    val registerBabyResponse by lazy {
        MutableLiveData<Boolean>()
    }

    val registerBabyWithImage by lazy {
        MutableLiveData<String>()
    }

    val notificationAlerts by lazy {
        MutableLiveData<Boolean>()
    }



    fun registerBaby(
        email: String,
        nombre:String,
        apellidoPaterno: String,
        apellidoMaterno: String,
        sexo: String,
        peso: String,
        edad: String,
        hardwareVinculado: String,
        imageUuid: String
    ){
        val data = hashMapOf(
            "nombre" to nombre,
            "apellidoPaterno" to apellidoPaterno,
            "apellidoMaterno" to apellidoMaterno,
            "edad" to edad,
            "monitor" to "0",
            "peso" to peso,
            "sexo" to sexo,
            "imageId" to imageUuid,
            "hardwareVinculado" to hardwareVinculado
        )
        babyRegisterUseCase.invoke(email, data)
            .addOnSuccessListener { documentReference ->
               registerBabyResponse.postValue(true)
            }
            .addOnFailureListener { e ->
                registerBabyResponse.postValue(false)
            }


    }

    fun saveBabyPhoto(imageUri: Uri) {
        val uuid = UUID.randomUUID().toString()
        val storageReference = FirebaseStorage.getInstance().getReference("image/${uuid}")
        storageReference.putFile(imageUri)
                .addOnSuccessListener {
                    registerBabyWithImage.postValue(uuid)
                }.addOnFailureListener {
                    notificationAlerts.postValue(false)
                }


    }

    }








