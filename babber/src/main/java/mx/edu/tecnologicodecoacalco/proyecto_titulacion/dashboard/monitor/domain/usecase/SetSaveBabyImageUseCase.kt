package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.usecase

import android.net.Uri
import com.google.firebase.storage.UploadTask
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.data.repository.MonitorRepositoryImp

class SetSaveBabyImageUseCase {

    private val repository by lazy {
        MonitorRepositoryImp()
    }

    operator fun invoke(uri: Uri, imageId: String): UploadTask{
        return repository.setSaveBabyImage(uri, imageId)
    }

}