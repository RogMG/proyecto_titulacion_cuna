package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.model.BabyDTO

class MonitorFragmentViewModel: ViewModel() {

    val babyModel by lazy {
        MutableLiveData<MutableList<BabyDTO>>()
    }

    fun getBabyInfo(){
        val mutableList = mutableListOf<BabyDTO>(
            BabyDTO("Rogelio"),
            BabyDTO("Osvaldo")
        )
        babyModel.postValue(mutableList)
    }





}