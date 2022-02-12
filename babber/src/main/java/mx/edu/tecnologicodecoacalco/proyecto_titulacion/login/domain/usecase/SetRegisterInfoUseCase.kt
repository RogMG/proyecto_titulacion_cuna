package mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.domain.usecase

import com.google.android.gms.tasks.Task
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.repository.RegisterRepositoryImp
import java.io.Serializable

class SetRegisterInfoUseCase {

    private val repository by lazy {
        RegisterRepositoryImp()
    }

    operator fun invoke(email: String, data: HashMap<String, Serializable>): Task<Void> {
        return repository.setRegisterInfo(email, data)
    }

}