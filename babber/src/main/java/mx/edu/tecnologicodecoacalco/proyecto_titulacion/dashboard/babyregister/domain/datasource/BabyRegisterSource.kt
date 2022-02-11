package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.babyregister.domain.datasource

import com.google.firebase.firestore.FirebaseFirestore

interface BabyRegisterSource {
    fun getFirebaseFromService(): FirebaseFirestore
}