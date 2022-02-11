package mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.domain.service.LoginService

class LoginServiceImp: LoginService {

    private lateinit var auth: FirebaseAuth

    override fun getAuthServiceFirebase(): FirebaseAuth {
        auth = Firebase.auth
        return auth
    }

}