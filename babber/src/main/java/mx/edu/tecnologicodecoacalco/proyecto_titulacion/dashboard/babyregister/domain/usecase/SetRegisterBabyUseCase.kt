package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.babyregister.domain.usecase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.babyregister.data.repository.BabyRegisterRepositoryImp
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.babyregister.domain.repository.BabyRegisterRepository

class SetRegisterBabyUseCase {

    private val repository by lazy {
        BabyRegisterRepositoryImp()
    }

    operator fun invoke(email: String, data: HashMap<String, String>): Task<DocumentReference> {
        return repository.registerBabyInFireStore(email,data)
    }

}