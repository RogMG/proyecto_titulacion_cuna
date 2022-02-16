package mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.domain.usecase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.data.repository.MonitorRepositoryImp

class GetBabyDataUseCase {

    private val repository by lazy {
        MonitorRepositoryImp()
    }

    operator fun invoke(email: String): Task<QuerySnapshot> {
        return repository.getBabyDataFromFirestore(email)
    }

}