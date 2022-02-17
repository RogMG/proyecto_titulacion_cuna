package mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.data.datasource

import com.google.firebase.firestore.FirebaseFirestore
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.data.service.AppConnectionRemoteService
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.domain.datasource.AppConnectionData

class AppConnectionDataSource: AppConnectionData {

    private val service by lazy { AppConnectionRemoteService() }

    override fun getFirebaseFromService(): FirebaseFirestore {
        return service.getFireStoreService()
    }

}