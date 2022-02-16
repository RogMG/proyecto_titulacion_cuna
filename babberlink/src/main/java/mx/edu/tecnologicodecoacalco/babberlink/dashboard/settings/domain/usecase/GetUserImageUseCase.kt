package mx.edu.tecnologicodecoacalco.babberlink.dashboard.settings.domain.usecase

import com.google.firebase.storage.StorageReference
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.settings.data.repository.SettingsRepositoryImp

class GetUserImageUseCase {

    private val repository by lazy {
        SettingsRepositoryImp()
    }

    operator fun invoke(id: String): StorageReference{
        return repository.getImageFromStorage(id)
    }

}