package mx.edu.tecnologicodecoacalco.babberlink.login.data.datasource

import com.google.firebase.auth.FirebaseAuth
import mx.edu.tecnologicodecoacalco.babberlink.login.data.service.LoginServiceImp
import mx.edu.tecnologicodecoacalco.babberlink.login.domain.datasource.LoginDataSource

class LoginDataSourceImp: LoginDataSource {

    private val service by lazy { LoginServiceImp() }

    override fun getLoginFromService(): FirebaseAuth {
        return service.getAuthServiceFirebase()
    }

}