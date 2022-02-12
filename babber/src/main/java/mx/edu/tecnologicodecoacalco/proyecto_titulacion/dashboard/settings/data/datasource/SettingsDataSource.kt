package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.settings.data.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.data.service.AdvicesRemoteService
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.domain.datasource.AdvicesSource
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.babyregister.data.services.BabyRegisterRemoteService
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.babyregister.domain.datasource.BabyRegisterSource
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.settings.data.services.SettingsRemoteService
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.settings.domain.datasource.SettingsSource
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.service.LoginServiceImp

class SettingsDataSource: SettingsSource {

    private val service by lazy { SettingsRemoteService() }

    override fun getFirebaseFromService(): FirebaseFirestore {
        return service.getFireStoreService()
    }

    override fun getFirebaseStorageFromService(): FirebaseStorage {
        return service.getFirebaseStorageService()
    }


}