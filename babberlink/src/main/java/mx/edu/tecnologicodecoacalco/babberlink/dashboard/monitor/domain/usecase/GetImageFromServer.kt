package mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.domain.usecase

import com.google.firebase.storage.StorageReference
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.data.repository.MonitorRepositoryImp

class GetImageFromServer {

    private val repository by lazy {
        MonitorRepositoryImp()
    }

    operator fun invoke(id: String): StorageReference{
        return repository.getImageFromStorage(id)
    }

}