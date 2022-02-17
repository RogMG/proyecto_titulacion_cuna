package mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.domain.usecase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.data.repository.MonitorRepositoryImp

class GetUserDataUseCase {

    private val repository by lazy {
        MonitorRepositoryImp()
    }

    operator fun invoke(token: String): Task<QuerySnapshot> {
        return repository.getUserData(token)
    }

}