package mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.domain.model.UserModelDTO
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.domain.usecase.GetUserDataUseCase
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.domain.usecase.SetUserTokenUseCase

class AppConnectionFragmentViewModel: ViewModel() {

    val tokenAddedResponse by lazy {
        MutableLiveData<Boolean>()
    }
    val userDataResponse by lazy {
        MutableLiveData<MutableList<UserModelDTO>>()
    }

    private val saveUserTokenUseCase by lazy {
        SetUserTokenUseCase()
    }
    private val getUserDataUseCase by lazy {
        GetUserDataUseCase()
    }
    fun addUserTokenToData(email:String, token: String){
        saveUserTokenUseCase.invoke(email, token)
            .addOnSuccessListener {
                tokenAddedResponse.postValue(true)
            }.addOnFailureListener {
                tokenAddedResponse.postValue(false)
            }
    }
    fun getUserData(token: String){
        val dataList = mutableListOf<UserModelDTO>()
        getUserDataUseCase.invoke(token)
            .addOnSuccessListener {
                if(!it.isEmpty) {
                    for (snapshot in it) {
                        dataList.add(
                            snapshot.toObject(
                                UserModelDTO::class.java
                            )
                        )
                    }
                    userDataResponse.postValue(dataList)
                }
            }.addOnFailureListener {
                userDataResponse.postValue(mutableListOf())
            }
    }

}