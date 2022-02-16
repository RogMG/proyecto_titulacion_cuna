package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.usecase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.data.repository.MonitorRepositoryImp

class GetUserNumberUseCase {
    private val repository by lazy {
        MonitorRepositoryImp()
    }

    operator fun invoke(email: String): Task<DocumentSnapshot> {
        return repository.getUserPhone(email)
    }


}