package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.appconection.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.appconection.domain.model.UserModelDTO
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.appconection.domain.usecase.GetUserDataUseCase

class AppConnectionFragmentViewModel: ViewModel() {

    private val getUserdataUseCase by lazy {
        GetUserDataUseCase()
    }

    val userDataResponse by lazy{
        MutableLiveData<UserModelDTO>()
    }

    fun getUserData(email: String){
        getUserdataUseCase.invoke(email)
            .addOnSuccessListener {
                val userData = it.toObject(
                    UserModelDTO::class.java
                )
                userDataResponse.postValue(userData)
            }
    }




}