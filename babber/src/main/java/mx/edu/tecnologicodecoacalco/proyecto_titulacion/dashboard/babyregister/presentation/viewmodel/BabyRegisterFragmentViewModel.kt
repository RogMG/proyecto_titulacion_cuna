package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.babyregister.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.babyregister.domain.usecase.SetRegisterBabyUseCase

class BabyRegisterFragmentViewModel: ViewModel() {

    private val babyRegisterUseCase by lazy {
        SetRegisterBabyUseCase()
    }

    val registerBabyResponse by lazy {
        MutableLiveData<Boolean>()
    }


    fun registerBaby(
        email: String,
        nombre:String,
        apellidoPaterno: String,
        apellidoMaterno: String,
        sexo: String,
        peso: String,
        edad: String,
        hardwareVinculado: String
    ){
        val data = hashMapOf(
            "nombre" to nombre,
            "apellidoPaterno" to apellidoPaterno,
            "apellidoMaterno" to apellidoMaterno,
            "edad" to edad,
            "monitor" to "0",
            "peso" to peso,
            "sexo" to sexo,
            "hardwareVinculado" to hardwareVinculado
        )
        babyRegisterUseCase.invoke(email, data)
            .addOnSuccessListener { documentReference ->
               registerBabyResponse.postValue(true)
            }
            .addOnFailureListener { e ->
                registerBabyResponse.postValue(false)
            }


    }






}