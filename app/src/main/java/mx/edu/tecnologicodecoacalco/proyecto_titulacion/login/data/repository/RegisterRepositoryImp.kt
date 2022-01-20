package mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.repository

import android.app.Activity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.datasource.LoginDataSourceImp
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.datasource.RegisterDataSourceImp
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.domain.repository.RegisterRepository

class RegisterRepositoryImp: RegisterRepository {

    private val dataSource by lazy { RegisterDataSourceImp() }

    override fun setRegisterAuth(
        name: String,
        dadLastName: String,
        momLastName: String,
        password: String,
        phone: String,
        email: String,
        context: Activity
    ): Task<AuthResult> {
        val auth = dataSource.setRegisterFromService()
        return auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(context) { }
    }

}