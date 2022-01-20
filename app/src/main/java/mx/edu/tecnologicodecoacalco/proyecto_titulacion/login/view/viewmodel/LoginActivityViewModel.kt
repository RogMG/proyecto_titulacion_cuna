package mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.view.viewmodel

import android.app.Activity
import android.app.AutomaticZenRule
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.model.LoginModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.domain.usecase.GetLoginAuthUseCase

class LoginActivityViewModel: ViewModel() {

    private val getLoginUseCase by lazy { GetLoginAuthUseCase() }

    fun getLoginAuth(email: String, password: String, context: Activity): LoginModel{
        val useCase = getLoginUseCase.invoke(email, password, context)
        if(useCase.isSuccessful){
            return LoginModel(true, "Si iniciamos sesion")
        }else{
            return LoginModel(false, "No iniciamos sesion")
        }
    }

}
