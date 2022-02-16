package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.appconection.domain.service

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

interface AppConnectionService {
    fun getFireStoreService(): FirebaseFirestore
}