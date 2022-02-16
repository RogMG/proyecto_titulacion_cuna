package mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.domain.model.BabyDTO
import io.reactivex.rxjava3.disposables.CompositeDisposable
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.domain.model.BabyIdModel
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.domain.model.BabyModel
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.domain.model.BabyMonitorDTO
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.domain.usecase.GetBabyDataUseCase
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.domain.usecase.GetBabyLpmListenerUseCase
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.domain.usecase.GetBabyLpmUseCase


class MonitorFragmentViewModel: ViewModel() {

    private val getBabyLpmUseCase by lazy{
        GetBabyLpmUseCase()
    }

    private val getDataUseCase by lazy{
        GetBabyDataUseCase()
    }

    private val getBabyLpmWithListenerUsecase by lazy{
        GetBabyLpmListenerUseCase()
    }

    private val disposable by lazy {
        CompositeDisposable()
    }



    val babyData by lazy {
        MutableLiveData<MutableList<BabyDTO>>()
    }
    val babyUpdatedData by lazy {
        MutableLiveData<MutableList<BabyDTO>>()
    }

    val babyMonitorData by lazy {
        MutableLiveData<BabyModel>()
    }

    private var counter = 1F


    fun getBabyInfo(email: String) {
        val babyDataList = mutableListOf<BabyDTO>()
        val data = getDataUseCase.invoke(email)
            data.addOnSuccessListener {
                if(!it.isEmpty){
                    for (snapshot in it) {
                        babyDataList.add(
                            snapshot.toObject(
                                BabyDTO::class.java
                            )
                        )
                    }
                }else{
                    babyDataList
                }
                babyData.postValue(babyDataList)
            }
            .addOnFailureListener {
                it.printStackTrace()
            }
    }

    fun getBabyInfoUpdate(email: String) {
        val babyDataList = mutableListOf<BabyDTO>()
        val data = getDataUseCase.invoke(email)
        data.addOnSuccessListener {
            if(!it.isEmpty){
                for (snapshot in it) {
                    babyDataList.add(
                        snapshot.toObject(
                            BabyDTO::class.java
                        )
                    )
                }
            }else{
                babyDataList
            }
            babyUpdatedData.postValue(babyDataList)
        }
            .addOnFailureListener {
                it.printStackTrace()
            }
    }

    fun getLpmBabyFromServiceWithListener(email: String){
        val data = getBabyLpmWithListenerUsecase.invoke(email)
        val dataList = mutableListOf<BabyMonitorDTO>()
        val idModel = mutableListOf<BabyIdModel>()
        val nameModel = mutableListOf<String>()
        data.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }
            if (snapshot != null ) {
                for(documents in snapshot){
                    documents.id.let {
                        idModel.add(BabyIdModel(it))
                    }
                    documents.getString("nombre")?.let {
                        nameModel.add(it)
                    }
                    documents.getString("monitor")?.let {
                        dataList.add(BabyMonitorDTO(it))
                    }
                }
            }
            val matchModel = BabyModel(idModel,dataList,nameModel)
            babyMonitorData.postValue(matchModel)
        }
    }



    fun stopFromEmitting(){
        disposable.dispose()
    }




}