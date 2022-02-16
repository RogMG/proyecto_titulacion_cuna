package mx.edu.tecnologicodecoacalco.babberlink.login.presentation.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.edu.tecnologicodecoacalco.babberlink.login.data.model.LoginModel
import mx.edu.tecnologicodecoacalco.babberlink.login.domain.usecase.GetLoginAuthUseCase

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
