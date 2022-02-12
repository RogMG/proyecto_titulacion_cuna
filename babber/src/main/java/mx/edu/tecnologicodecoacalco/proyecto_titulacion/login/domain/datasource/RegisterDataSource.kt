package mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.domain.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

interface RegisterDataSource {
    fun setRegisterFromService(): FirebaseAuth
    fun registerUserToFirestore(): FirebaseFirestore
}