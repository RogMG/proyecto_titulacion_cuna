package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.babyregister.domain.services

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

interface BabyRegisterService {
    fun getFireStoreService(): FirebaseFirestore
}