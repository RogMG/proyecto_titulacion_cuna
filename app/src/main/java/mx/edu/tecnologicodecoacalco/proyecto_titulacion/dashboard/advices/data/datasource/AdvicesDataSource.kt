package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.data.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.data.service.AdvicesRemoteService
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.domain.datasource.AdvicesSource
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.service.LoginServiceImp

class AdvicesDataSource: AdvicesSource {

    private val service by lazy { AdvicesRemoteService() }

    override fun getFirebaseFromService(): FirebaseFirestore {
        return service.getFireStoreService()
    }


}