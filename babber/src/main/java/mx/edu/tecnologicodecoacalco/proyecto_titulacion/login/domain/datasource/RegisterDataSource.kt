package mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.domain.datasource

import com.google.firebase.auth.FirebaseAuth

interface RegisterDataSource {
    fun setRegisterFromService(): FirebaseAuth
}