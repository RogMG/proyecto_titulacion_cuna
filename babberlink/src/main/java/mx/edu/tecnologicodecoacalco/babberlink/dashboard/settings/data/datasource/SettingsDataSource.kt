package mx.edu.tecnologicodecoacalco.babberlink.dashboard.settings.data.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.settings.data.services.SettingsRemoteService
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.settings.domain.datasource.SettingsSource


class SettingsDataSource: SettingsSource {

    private val service by lazy { SettingsRemoteService() }

    override fun getFirebaseFromService(): FirebaseFirestore {
        return service.getFireStoreService()
    }

    override fun getFirebaseStorageFromService(): FirebaseStorage {
        return service.getFirebaseStorageService()
    }


}