package mx.edu.tecnologicodecoacalco.babberlink.login.domain.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

interface LoginService {
    fun getAuthServiceFirebase(): FirebaseAuth
    fun setRegisterUser(): FirebaseFirestore
}