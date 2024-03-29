package mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.domain.usecase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.data.repository.MonitorRepositoryImp

class GetTokensFromServerUseCase {

    private val repository by lazy {
        MonitorRepositoryImp()
    }

    operator fun invoke(email: String): Task<DocumentSnapshot> {
        return repository.getTokensFromServer(email)
    }

}