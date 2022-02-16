package mx.edu.tecnologicodecoacalco.babberlink.dashboard.settings.domain.usecase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.settings.data.repository.SettingsRepositoryImp

class GetUserDataUseCase {

    private val repository by lazy {
        SettingsRepositoryImp()
    }

    operator fun invoke(email: String): Task<DocumentSnapshot>{
        return repository.getUserDataFromFirestore(email)
    }

}