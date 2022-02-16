package mx.edu.tecnologicodecoacalco.babberlink.login.data.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.edu.tecnologicodecoacalco.babberlink.login.domain.service.LoginService

class LoginServiceImp: LoginService {

    private lateinit var auth: FirebaseAuth

    private val firestore by lazy {
        Firebase.firestore
    }

    override fun getAuthServiceFirebase(): FirebaseAuth {
        auth = Firebase.auth
        return auth
    }

    override fun setRegisterUser(): FirebaseFirestore {
        return firestore
    }

}