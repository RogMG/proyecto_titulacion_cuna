package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.domain.usecase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.data.repository.AdvicesRepositoryImp

class GetAdvicesDataUseCase {

    private val repository by lazy {
        AdvicesRepositoryImp()
    }

    operator fun invoke(): Task<QuerySnapshot> {
        return repository.getFireStoreData()
    }

}