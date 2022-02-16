package mx.edu.tecnologicodecoacalco.babberlink.login.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.edu.tecnologicodecoacalco.babberlink.login.domain.usecase.SetRegisterAuthUseCase
import mx.edu.tecnologicodecoacalco.babberlink.login.domain.usecase.SetRegisterInfoUseCase

class RegisterActivityViewModel: ViewModel() {

    val notificationResponse by lazy{
        MutableLiveData<Boolean>()
    }

    val registerAuthLiveData by lazy{
        MutableLiveData<Boolean>()
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
        ).addOnCompleteListener{
            if(it.isSuccessful){
                registerAuthLiveData.postValue(true)
            }else{
                notificationResponse.postValue(false)
            }
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