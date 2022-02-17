package mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.domain.model.UserModelDTO
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.domain.usecase.GetImageFromServer
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.domain.usecase.GetTokensFromServerUseCase
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.domain.usecase.GetUserDataUseCase
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.settings.domain.model.UserDataModel


class MonitorFragmentViewModel: ViewModel() {

    private val getDataUseCase by lazy{
        GetUserDataUseCase()
    }
    private val getTokensUseCase by lazy{
        GetTokensFromServerUseCase()
    }
    private val getImageFromServer by lazy{
        GetImageFromServer()
    }

    private val disposable by lazy {
        CompositeDisposable()
    }

    val userDataResponse by lazy {
        MutableLiveData<MutableList<UserModelDTO>>()
    }
    val userImageResponse by lazy {
        MutableLiveData<Uri>()
    }
    val getTokensResponse by lazy {
        MutableLiveData<UserDataModel>()
    }
    val notificationResponse by lazy {
        MutableLiveData<Boolean>()
    }

    fun getUserData(token: String) {
        val babyDataList = mutableListOf<UserModelDTO>()
        val data = getDataUseCase.invoke(token)
            data.addOnSuccessListener {
                if(!it.isEmpty){
                    for (snapshot in it) {
                        babyDataList.add(
                            snapshot.toObject(
                                UserModelDTO::class.java
                            )
                        )
                    }
                }
                userDataResponse.postValue(babyDataList)
            }
            .addOnFailureListener {
                it.printStackTrace()
            }
    }

    fun getTokensData(email: String){
        getTokensUseCase.invoke(email)
            .addOnSuccessListener {
                if(it.exists()){
                    val userData = it.toObject(
                        UserDataModel::class.java
                    )
                    getTokensResponse.postValue(userData)
                }
            }.addOnFailureListener {
                notificationResponse.postValue(false)
            }
    }

    fun getUserImage(id: String){
        getImageFromServer.invoke(id)
            .downloadUrl
            .addOnSuccessListener {
                userImageResponse.postValue(it)
            }.addOnFailureListener {
                userImageResponse.postValue(Uri.EMPTY)
            }

    }

    fun stopFromEmitting(){
        disposable.dispose()
    }




}