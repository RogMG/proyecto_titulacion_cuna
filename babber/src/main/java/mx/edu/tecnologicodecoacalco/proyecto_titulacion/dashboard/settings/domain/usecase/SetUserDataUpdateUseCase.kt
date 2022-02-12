package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.settings.domain.usecase

import com.google.android.gms.tasks.Task
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.settings.data.repository.SettingsRepositoryImp

class SetUserDataUpdateUseCase {

    private val repository by lazy {
        SettingsRepositoryImp()
    }

    operator fun invoke(email: String, data: HashMap<String, Any>): Task<Void> {
        return repository.setUserDataUpdate(email, data)
    }
}