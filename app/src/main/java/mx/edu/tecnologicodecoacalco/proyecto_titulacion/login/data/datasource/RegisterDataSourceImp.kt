package mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.datasource

import com.google.firebase.auth.FirebaseAuth
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.service.LoginServiceImp
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.domain.datasource.RegisterDataSource

class RegisterDataSourceImp: RegisterDataSource {

    private val service by lazy { LoginServiceImp() }

    override fun setRegisterFromService(): FirebaseAuth {
        return service.getAuthServiceFirebase()
    }
}