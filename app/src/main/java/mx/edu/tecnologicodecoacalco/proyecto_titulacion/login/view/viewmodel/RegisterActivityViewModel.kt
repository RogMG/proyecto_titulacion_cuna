package mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.view.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.model.LoginModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.model.RegisterModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.domain.usecase.SetRegisterAuthUseCase

class RegisterActivityViewModel: ViewModel() {

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
    ): RegisterModel {
        val useCase = setRegisterUseCase.invoke(
            name = name,
            dadLastName = dadLastName,
            momLastName = momLastName,
            password = password,
            phone = phone,
            email = email,
            context = context
        ).onSuccessTask {

        }

        return if(useCase.isSuccessful){
            RegisterModel(true, "Si creamos el usuario")
        }else{
            RegisterModel(false, "No creamos el usuario")
        }


    }




}