package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.domain.datasource

import com.google.firebase.firestore.FirebaseFirestore

interface AdvicesSource {
    fun getFirebaseFromService(): FirebaseFirestore
}