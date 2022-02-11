package mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.datasource

import com.google.firebase.auth.FirebaseAuth
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.service.LoginServiceImp
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.domain.datasource.LoginDataSource

class LoginDataSourceImp: LoginDataSource {

    private val service by lazy { LoginServiceImp() }

    override fun getLoginFromService(): FirebaseAuth {
        return service.getAuthServiceFirebase()
    }

}