package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.Entry
import io.reactivex.rxjava3.core.Observable
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.model.BabyDTO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.domain.model.AdvicesModelDTO
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.model.BabyMonitorDTO
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.usecase.GetBabyDataUseCase
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.usecase.GetBabyLpmListenerUseCase
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.usecase.GetBabyLpmUseCase
import java.util.concurrent.TimeUnit
import kotlin.random.Random.Default.nextInt


class MonitorFragmentViewModel: ViewModel() {

    private val getBabyLpmUseCase by lazy{
        GetBabyLpmUseCase()
    }
    private val getBabyLpmListenerUseCase by lazy{
        GetBabyLpmListenerUseCase()
    }

    private val getDataUseCase by lazy{
        GetBabyDataUseCase()
    }

    private val babyDataList by lazy {
        mutableListOf<BabyDTO>()
    }

    val babyMonitor by lazy {
        MutableLiveData<MutableList<MutableList<Entry>>>()
    }

    val babyData by lazy {
        MutableLiveData<MutableList<BabyDTO>>()
    }

    val babyMonitorData by lazy {
        MutableLiveData<MutableList<BabyMonitorDTO>>()
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

            }


    }


    fun getLpmBaby(email: String){
        Observable.interval(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { }
            .subscribe {
                getLpmBabyFromService(email)
            }

    }

    fun getLpmBabyFromService(email: String) {
        val data = getBabyLpmUseCase.invoke(email)
        val dataList = mutableListOf<BabyMonitorDTO>()
        data.addOnSuccessListener { it ->
            if(!it.isEmpty){
                for (snapshot in it) {
                    snapshot.getString("monitor")?.let {
                        dataList.add(BabyMonitorDTO(it))
                    }
                }
            }
            babyMonitorData.postValue(dataList)
        }.addOnFailureListener { }
    }


    fun computeBabyData(list: MutableList<BabyMonitorDTO>){
        if(babyInfo.isEmpty()){
          for(position in 0 until list.size){
              babyInfo.add(mutableListOf())
          }
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




}