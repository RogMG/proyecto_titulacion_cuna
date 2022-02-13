package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.usecase

import com.google.android.gms.tasks.Task
import com.google.firebase.storage.StorageReference
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.data.repository.MonitorRepositoryImp

class SetDeleteBabyImageUseCase {
    private val repository by lazy {
        MonitorRepositoryImp()
    }

    operator fun invoke(imageId: String): Task<Void> {
        return repository.setDeleteBabyImage(imageId)
    }

}