package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.babyregister.data.services

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.domain.service.AdvicesService
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.babyregister.domain.services.BabyRegisterService

class BabyRegisterRemoteService: BabyRegisterService {

    private val firestore by lazy {
        Firebase.firestore
    }

    override fun getFireStoreService(): FirebaseFirestore {
        return firestore
    }


}