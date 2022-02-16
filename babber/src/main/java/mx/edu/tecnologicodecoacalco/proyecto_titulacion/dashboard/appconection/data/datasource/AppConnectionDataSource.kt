package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.appconection.data.datasource

import com.google.firebase.firestore.FirebaseFirestore
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.data.service.AdvicesRemoteService
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.appconection.data.service.AppConnectionRemoteService
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.appconection.domain.datasource.AppConnectionData

class AppConnectionDataSource: AppConnectionData {

    private val service by lazy { AppConnectionRemoteService() }

    override fun getFirebaseFromService(): FirebaseFirestore {
        return service.getFireStoreService()
    }

}