package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.domain.model.AdvicesModelDTO
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.domain.usecase.GetAdvicesDataUseCase

class AdvicesFragmentViewModel: ViewModel() {

    val advicesData by lazy{
        MutableLiveData<MutableList<AdvicesModelDTO>>()
    }

    private val advicesList by lazy {
        mutableListOf<AdvicesModelDTO>()
    }

    private val getAdvicesUseCase by lazy{
        GetAdvicesDataUseCase()
    }

    fun getAdvicesData(){
        val data =  getAdvicesUseCase.invoke()
        data.addOnSuccessListener {
            if(!it.isEmpty){
                for (snapshot in it) {
                    advicesList.add(
                        snapshot.toObject(
                            AdvicesModelDTO::class.java
                        )
                    )
                }
            }else{
                advicesList
            }
            advicesData.postValue(advicesList)
        }
    }


}