package mx.edu.tecnologicodecoacalco.babberlink.login.data.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import mx.edu.tecnologicodecoacalco.babberlink.login.data.service.LoginServiceImp
import mx.edu.tecnologicodecoacalco.babberlink.login.domain.datasource.RegisterDataSource

class RegisterDataSourceImp: RegisterDataSource {

    private val service by lazy { LoginServiceImp() }

    override fun setRegisterFromService(): FirebaseAuth {
        return service.getAuthServiceFirebase()
    }

    override fun registerUserToFirestore(): FirebaseFirestore {
        return service.setRegisterUser()
    }
}