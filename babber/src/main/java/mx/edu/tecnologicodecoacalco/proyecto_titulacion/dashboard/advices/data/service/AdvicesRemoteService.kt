package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.data.service

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.domain.service.AdvicesService

class AdvicesRemoteService: AdvicesService {

    private val firestore by lazy {
        Firebase.firestore
    }

    override fun getFireStoreService(): FirebaseFirestore {
        return firestore
    }


}