package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.data.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.data.service.AdvicesRemoteService
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.data.service.MonitorRemoteService
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.datasource.MonitorData

class MonitorDataSource: MonitorData {

    private val service by lazy { MonitorRemoteService() }

    override fun getFirebaseFromService(): FirebaseFirestore {
        return service.getFireStoreService()
    }

    override fun getFirebaseStorageFromService(): FirebaseStorage {
        return service.getFirebaseStorageService()
    }



}