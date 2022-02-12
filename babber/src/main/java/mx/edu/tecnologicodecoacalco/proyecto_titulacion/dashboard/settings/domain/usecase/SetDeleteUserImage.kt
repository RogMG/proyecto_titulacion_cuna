package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.settings.domain.usecase

import com.google.android.gms.tasks.Task
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.settings.data.repository.SettingsRepositoryImp

class SetDeleteUserImage {

    private val repository by lazy {
        SettingsRepositoryImp()
    }

    operator fun invoke(id: String): Task<Void> {
        return repository.setDeleteUserImage(id)
    }

}