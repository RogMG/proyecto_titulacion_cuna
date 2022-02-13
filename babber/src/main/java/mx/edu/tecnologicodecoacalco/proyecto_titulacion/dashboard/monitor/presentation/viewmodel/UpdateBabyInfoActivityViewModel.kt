package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.usecase.GetImageFromStorageUseCase
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.usecase.SetDeleteBabyImageUseCase
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.usecase.SetSaveBabyImageUseCase
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.usecase.SetUpdateBabyDataUseCase
import java.util.*
import kotlin.collections.HashMap

class UpdateBabyInfoActivityViewModel: ViewModel() {

    private val getImageFromStorageUseCase by lazy {
        GetImageFromStorageUseCase()
    }
    private val setDeleteBabyImageUseCase by lazy {
        SetDeleteBabyImageUseCase()
    }
    private val setUpdateBabyDataUseCase by lazy {
        SetUpdateBabyDataUseCase()
    }
    private val setSaveBabyImageUsecase by lazy {
        SetSaveBabyImageUseCase()
    }


    val imageFromStorageResponse by lazy {
        MutableLiveData<Uri>()
    }
    val deleteBabyImageResponse by lazy {
        MutableLiveData<Boolean>()
    }
    val setBabyUserImageId by lazy {
        MutableLiveData<String>()
    }
    val setSaveBabyUserImageId by lazy {
        MutableLiveData<String>()
    }
    val notificationResponse by lazy {
        MutableLiveData<Boolean>()
    }


    fun getImageFromStorage(id: String){
        getImageFromStorageUseCase.invoke(id)
            .downloadUrl
            .addOnSuccessListener {
                imageFromStorageResponse.postValue(it)
                setBabyUserImageId.postValue(id)
            }
            .addOnFailureListener {
                notificationResponse.postValue(false)
            }
    }
    fun setDeleteBabyImage(imageId: String){
        setDeleteBabyImageUseCase.invoke(imageId)
            .addOnSuccessListener {
                deleteBabyImageResponse.postValue(true)
            }.addOnFailureListener {
                notificationResponse.postValue(false)
            }

    }
    fun setUpdateBabyInfo(email: String, id: String, data: HashMap<String, Any>){
        setUpdateBabyDataUseCase.invoke(email, id, data)
            .addOnSuccessListener {
                notificationResponse.postValue(true)
            }.addOnFailureListener {
                notificationResponse.postValue(false)
            }
    }

    fun saveImageBaby(image: Uri){
        val uuid = UUID.randomUUID().toString()
        setSaveBabyImageUsecase.invoke(image, uuid)
            .addOnSuccessListener {
                setSaveBabyUserImageId.postValue(uuid)
            }.addOnFailureListener{
                notificationResponse.postValue(false)
            }
    }







}