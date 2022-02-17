package mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.domain.usecase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.data.repository.AppConnectionRepositoryImpl

class SetUserTokenUseCase{

    private val repository by lazy {
        AppConnectionRepositoryImpl()
    }

    operator fun invoke(email: String, token: String): Task<Void> {
        return repository.saveTokenInUser(email, token)
    }

}