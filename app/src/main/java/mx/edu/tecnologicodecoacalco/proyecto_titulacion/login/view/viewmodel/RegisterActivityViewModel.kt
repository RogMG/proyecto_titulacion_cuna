package mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.view.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.model.LoginModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.model.RegisterModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.domain.usecase.SetRegisterAuthUseCase

class RegisterActivityViewModel: ViewModel() {

    val registerLiveData: MutableLiveData<RegisterModel> by lazy{
        MutableLiveData<RegisterModel>()
    }

    private val setRegisterUseCase by lazy {
        SetRegisterAuthUseCase()
    }

    fun setRegisterUser(
        name: String,
        dadLastName: String,
        momLastName: String,
        password: String,
        phone: String,
        email: String,
        context: Activity
    ) {
        setRegisterUseCase.invoke(
            name = name,
            dadLastName = dadLastName,
            momLastName = momLastName,
            password = password,
            phone = phone,
            email = email,
            context = context
        ).addOnCompleteListener{
            if(it.isSuccessful){
                registerLiveData.postValue(
                    RegisterModel(
                        true,
                        "El usuario no se creo con exito"
                    )
                )
            }else{
                registerLiveData.postValue(
                    RegisterModel(
                        true,
                        "El usuario se creo con exito"
                    )
                )
            }
        }



    }




}