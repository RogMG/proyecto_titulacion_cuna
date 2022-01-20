package mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.repository

import android.app.Activity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.datasource.LoginDataSourceImp
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.model.LoginModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.domain.repository.LoginRepository

class LoginRepositoryImp: LoginRepository {

    private val dataSource by lazy { LoginDataSourceImp() }

    override fun getLogin(email: String, password: String, context: Activity): Task<AuthResult> {
        val auth = dataSource.getLoginFromService()
       return auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(context){ }
    }




}