package mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.domain.service

import com.google.firebase.auth.FirebaseAuth

interface LoginService {
    fun getAuthServiceFirebase(): FirebaseAuth
}