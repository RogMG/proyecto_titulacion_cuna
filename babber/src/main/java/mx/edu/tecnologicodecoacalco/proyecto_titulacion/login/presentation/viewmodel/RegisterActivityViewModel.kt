package mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.presentation.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.model.RegisterModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.domain.usecase.SetRegisterAuthUseCase
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.domain.usecase.SetRegisterInfoUseCase
import java.io.Serializable

class RegisterActivityViewModel: ViewModel() {

    val notificationResponse by lazy{
        MutableLiveData<Boolean>()
    }
    val registerAuthLiveData by lazy{
        MutableLiveData<Boolean>()
    }
    val registerUidUser by lazy{
        MutableLiveData<String>()
    }

    private val setRegisterUseCase by lazy {
        SetRegisterAuthUseCase()
    }

    private val setRegisterInfoUseCase by lazy {
        SetRegisterInfoUseCase()
    }


    fun setRegisterUser(
        password: String,
        email: String
    ) {
        setRegisterUseCase.invoke(
            password = password,
            email = email
        ).addOnSuccessListener{
            registerUidUser.postValue(it.user?.uid)
        }.addOnFailureListener {
            registerAuthLiveData.postValue(false)
        }
    }

    fun setRegisterUserInfo(email: String, data: HashMap<String, Any>){
        setRegisterInfoUseCase.invoke(
            email, data
        ).addOnSuccessListener {
            notificationResponse.postValue(true)
        }.addOnFailureListener {
            notificationResponse.postValue(false)
        }
    }




}