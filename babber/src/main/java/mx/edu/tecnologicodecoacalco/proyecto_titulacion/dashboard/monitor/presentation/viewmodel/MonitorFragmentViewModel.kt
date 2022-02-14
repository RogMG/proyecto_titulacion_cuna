package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.Entry
import io.reactivex.rxjava3.core.Observable
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.model.BabyDTO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.model.BabyIdModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.model.BabyModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.model.BabyMonitorDTO
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.usecase.GetBabyDataUseCase
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.usecase.GetBabyLpmListenerUseCase
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.usecase.GetBabyLpmUseCase
import java.util.concurrent.TimeUnit


class MonitorFragmentViewModel: ViewModel() {

    private val getBabyLpmUseCase by lazy{
        GetBabyLpmUseCase()
    }

    private val getDataUseCase by lazy{
        GetBabyDataUseCase()
    }

    private val babyDataList by lazy {
        mutableListOf<BabyDTO>()
    }

    private val getBabyLpmWithListenerUsecase by lazy{
        GetBabyLpmListenerUseCase()
    }

    private val disposable by lazy {
        CompositeDisposable()
    }

    val babyMonitor by lazy {
        MutableLiveData<MutableList<MutableList<Entry>>>()
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

    private val babyInfo by lazy {
        mutableListOf<MutableList<Entry>>()
    }

    fun getBabyInfo(email: String) {
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

    fun getLpmBaby(email: String){
        disposable.add(Observable.interval(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { }
            .subscribe {
                getLpmBabyFromService(email)
            })
    }

    fun getLpmBabyFromService(email: String) {
        val data = getBabyLpmUseCase.invoke(email)
        val dataList = mutableListOf<BabyMonitorDTO>()
        val idModel = mutableListOf<BabyIdModel>()
        val nameModel = mutableListOf<String>()
        data.addOnSuccessListener { it ->
            if(!it.isEmpty){
                for (snapshot in it) {
                    snapshot.id.let {
                        idModel.add(BabyIdModel(it))
                    }
                    snapshot.getString("nombre")?.let {
                        nameModel.add(it)
                    }
                    snapshot.getString("monitor")?.let {
                        dataList.add(BabyMonitorDTO(it))
                    }
                }
            }
            val matchModel = BabyModel(idModel,dataList,nameModel)
            babyMonitorData.postValue(matchModel)
        }.addOnFailureListener { it.printStackTrace() }
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


    fun computeBabyData(list: MutableList<BabyMonitorDTO>, email: String){
        if(babyInfo.isEmpty()){
          for(position in 0 until list.size){
              babyInfo.add(mutableListOf())
          }
        }
        if(babyInfo.size != list.size){
            getBabyInfo(email)
            return
        }
        for(position in 0 until list.size){
          if(babyInfo[position].size > 10){
                babyInfo[position].removeAt(0)
                val value = Entry(counter, list[position].monitor.toFloat() )
                babyInfo[position].add(value)
           }else{
             val value = Entry(counter, list[position].monitor.toFloat() )
              babyInfo[position].add(value)
           }
        }
        babyMonitor.postValue(babyInfo)
        counter += 1

    }

    fun stopFromEmitting(){
        disposable.dispose()
    }




}