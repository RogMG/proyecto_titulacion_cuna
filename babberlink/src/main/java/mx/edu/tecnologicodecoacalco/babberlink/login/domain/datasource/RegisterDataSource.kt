package mx.edu.tecnologicodecoacalco.babberlink.login.domain.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

interface RegisterDataSource {
    fun setRegisterFromService(): FirebaseAuth
    fun registerUserToFirestore(): FirebaseFirestore
}