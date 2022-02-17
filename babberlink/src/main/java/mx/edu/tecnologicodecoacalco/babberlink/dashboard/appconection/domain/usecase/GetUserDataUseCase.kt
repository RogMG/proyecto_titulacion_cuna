package mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.domain.usecase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.data.repository.AppConnectionRepositoryImpl

class GetUserDataUseCase {

    private val repository by lazy {
        AppConnectionRepositoryImpl()
    }

    operator fun invoke(token: String): Task<QuerySnapshot> {
        return repository.getUserData(token)
    }

}