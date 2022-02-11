package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.babyregister.data.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.data.service.AdvicesRemoteService
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.domain.datasource.AdvicesSource
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.babyregister.data.services.BabyRegisterRemoteService
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.babyregister.domain.datasource.BabyRegisterSource
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.service.LoginServiceImp

class BabyRegisterDataSource: BabyRegisterSource {

    private val service by lazy { BabyRegisterRemoteService() }

    override fun getFirebaseFromService(): FirebaseFirestore {
        return service.getFireStoreService()
    }


}