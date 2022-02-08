package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.domain.service

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

interface AdvicesService {
    fun getFireStoreService(): FirebaseFirestore
}