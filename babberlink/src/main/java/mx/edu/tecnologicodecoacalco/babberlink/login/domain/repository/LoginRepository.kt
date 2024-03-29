package mx.edu.tecnologicodecoacalco.babberlink.login.domain.repository

import android.app.Activity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import mx.edu.tecnologicodecoacalco.babberlink.login.data.model.LoginModel

interface LoginRepository {
    fun getLogin(email: String, password: String, context: Activity): Task<AuthResult>
}