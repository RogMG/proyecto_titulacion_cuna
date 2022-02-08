package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.Entry
import io.reactivex.rxjava3.core.Observable
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.model.BabyDTO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import kotlin.random.Random.Default.nextInt


class MonitorFragmentViewModel: ViewModel() {

    val babyMonitor by lazy {
        MutableLiveData<MutableList<Entry>>()
    }

    private var counter = 1F

    private lateinit var babyInfo: MutableList<Entry>

    fun getBabyInfo(): MutableList<BabyDTO> {
        val mutableList = mutableListOf(
            BabyDTO("Rogelio"),
            BabyDTO("Osvaldo")
        )
        return mutableList
    }

    fun getFirstLpm(): MutableList<Entry>{
        babyInfo = mutableListOf(
            Entry(0F, 70F)
        )
       return babyInfo
    }

    fun getLpmBaby(){
        Observable.interval(1, TimeUnit.SECONDS) //emits item every second
            .observeOn(AndroidSchedulers.mainThread()) //switches thread from computation (interval's default) to UI
            .subscribe { i: Long? ->
                if(babyInfo.size > 10){
                    babyInfo.removeAt(0)
                    val lpmValue = nextInt(70,110).toFloat()
                    val value = Entry(counter, lpmValue )
                    babyInfo.add(value)
                    babyMonitor.postValue(babyInfo)
                }else{
                    val lpmValue = nextInt(70,110).toFloat()
                    val value = Entry(counter, lpmValue )
                    babyInfo.add(value)
                    babyMonitor.postValue(babyInfo)
                }
                counter += 1
            }


    }





}