package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.appconection.domain.usecase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.appconection.data.repository.AppConnectionRepositoryImpl

class GetUserDataUseCase{

    private val repository by lazy {
        AppConnectionRepositoryImpl()
    }

    operator fun invoke(email: String): Task<DocumentSnapshot> {
        return repository.getUserToken(email)
    }

}