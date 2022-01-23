package mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.domain.repository

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.model.RegisterModel

interface RegisterRepository {
    fun setRegisterAuth(
        name: String,
        dadLastName: String,
        momLastName: String,
        password: String,
        phone: String,
        email: String,
        context: Activity
    ): Task<AuthResult>
}