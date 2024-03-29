package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.usecase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.StorageReference
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.data.repository.MonitorRepositoryImp

class GetImageFromStorageUseCase {
    private val repository by lazy {
        MonitorRepositoryImp()
    }

    operator fun invoke(id: String): StorageReference {
        return repository.getImageFromStorage(id)
    }

}