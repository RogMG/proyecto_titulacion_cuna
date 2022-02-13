package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.settings.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.settings.domain.model.UserDataModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.settings.domain.usecase.GetUserDataUseCase
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.settings.domain.usecase.GetUserImageUseCase
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.settings.domain.usecase.SetDeleteUserImage
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.settings.domain.usecase.SetUserDataUpdateUseCase
import java.util.*
import kotlin.collections.HashMap

class SettingsFragmentViewModel: ViewModel() {

    private val getUserDataUseCase by lazy{
        GetUserDataUseCase()
    }
    private val getUserImageUseCase by lazy{
        GetUserImageUseCase()
    }
    private val setUserdataUpdateUseCase by lazy {
        SetUserDataUpdateUseCase()
    }
    private val setUserDeleteImage by lazy {
        SetDeleteUserImage()
    }

    val userDataResponse by lazy {
        MutableLiveData<UserDataModel>()
    }

    val userImageResponse by lazy {
        MutableLiveData<Uri>()
    }

    val babyUserImageId by lazy {
        MutableLiveData<String>()
    }
    val saveBabyUserImageId by lazy {
        MutableLiveData<String>()
    }

    val notificationResponse by lazy {
        MutableLiveData<Boolean>()
    }

    val deletedUserImage by lazy {
        MutableLiveData<Boolean>()
    }



    fun getUserData(email: String){
        getUserDataUseCase.invoke(email)
            .addOnSuccessListener {
                val userData = it.toObject(
                    UserDataModel::class.java
                )
                userDataResponse.postValue(userData)
            }.addOnFailureListener {
                notificationResponse.postValue(false)
            }
    }

    fun getUserImage(id: String){
        getUserImageUseCase.invoke(id)
            .downloadUrl
            .addOnSuccessListener {
                userImageResponse.postValue(it)
                saveBabyUserImageId.postValue(id)
            }.addOnFailureListener {
                notificationResponse.postValue(false)
            }
    }

    fun setUserDataUpdate(email: String, data: HashMap<String, Any>){
        setUserdataUpdateUseCase.invoke(email, data)
            .addOnSuccessListener {
                notificationResponse.postValue(true)
            }.addOnFailureListener {
                notificationResponse.postValue(false)
            }
    }

    fun setDeleteUserImage(id: String){
        setUserDeleteImage.invoke(id)
            .addOnSuccessListener {
                deletedUserImage.postValue(true)
            }.addOnFailureListener {
                deletedUserImage.postValue(false)
            }
    }

    fun saveBabyPhoto(imageUri: Uri) {
        val uuid = UUID.randomUUID().toString()
        val storageReference = FirebaseStorage.getInstance().getReference("image/${uuid}")
        storageReference.putFile(imageUri)
            .addOnSuccessListener {
                babyUserImageId.postValue(uuid)
            }.addOnFailureListener {
                notificationResponse.postValue(false)
            }
    }


}