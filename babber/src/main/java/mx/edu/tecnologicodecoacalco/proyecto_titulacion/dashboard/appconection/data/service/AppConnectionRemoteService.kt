package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.appconection.data.service

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.appconection.domain.service.AppConnectionService

class AppConnectionRemoteService: AppConnectionService {

    private val firestore by lazy {
        Firebase.firestore
    }

    override fun getFireStoreService(): FirebaseFirestore {
        return firestore
    }


}