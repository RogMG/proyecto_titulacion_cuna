package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.usecase

import com.google.android.gms.tasks.Task
import com.google.firebase.storage.StorageReference
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.data.repository.MonitorRepositoryImp

class SetUpdateBabyDataUseCase {
    private val repository by lazy {
        MonitorRepositoryImp()
    }

    operator fun invoke(email: String, id: String, data: HashMap<String, Any>): Task<Void> {
        return repository.setUpdateBabyInfo(email, id, data)
    }

}