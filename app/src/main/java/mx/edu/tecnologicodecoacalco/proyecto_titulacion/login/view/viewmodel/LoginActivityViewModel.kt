package mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.view.viewmodel

import android.app.Activity
import android.app.AutomaticZenRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.model.LoginModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.domain.usecase.GetLoginAuthUseCase

class LoginActivityViewModel: ViewModel() {

    val loginLiveData: MutableLiveData<LoginModel> by lazy {
        MutableLiveData<LoginModel>()
    }

    private val getLoginUseCase by lazy { GetLoginAuthUseCase() }

    fun getLoginAuth(email: String, password: String, context: Activity){
        getLoginUseCase.invoke(email, password, context)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    loginLiveData.postValue(
                        LoginModel(true, "Bienvenido")
                    )
                }else{
                    loginLiveData.postValue(
                        LoginModel(false, "Error en el inicio de sesión")
                    )
                }
            }

    }

}
