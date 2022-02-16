package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.appconection.domain.datasource

import com.google.firebase.firestore.FirebaseFirestore

interface AppConnectionData {
    fun getFirebaseFromService(): FirebaseFirestore
}