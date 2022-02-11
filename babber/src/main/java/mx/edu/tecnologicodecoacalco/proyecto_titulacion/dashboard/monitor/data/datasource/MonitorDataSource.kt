package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.data.datasource

import com.google.firebase.firestore.FirebaseFirestore
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.data.service.AdvicesRemoteService
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.datasource.MonitorData

class MonitorDataSource: MonitorData {

    private val service by lazy { AdvicesRemoteService() }

    override fun getFirebaseFromService(): FirebaseFirestore {
        return service.getFireStoreService()
    }

}