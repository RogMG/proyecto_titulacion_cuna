package mx.edu.tecnologicodecoacalco.babberlink.dashboard.settings.domain.usecase

import com.google.android.gms.tasks.Task
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.settings.data.repository.SettingsRepositoryImp

class SetDeleteUserImage {

    private val repository by lazy {
        SettingsRepositoryImp()
    }

    operator fun invoke(id: String): Task<Void> {
        return repository.setDeleteUserImage(id)
    }

}