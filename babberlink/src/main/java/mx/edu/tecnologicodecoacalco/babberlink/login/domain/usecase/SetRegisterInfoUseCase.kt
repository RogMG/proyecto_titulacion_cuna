package mx.edu.tecnologicodecoacalco.babberlink.login.domain.usecase

import com.google.android.gms.tasks.Task
import mx.edu.tecnologicodecoacalco.babberlink.login.data.repository.RegisterRepositoryImp
import java.io.Serializable

class SetRegisterInfoUseCase {

    private val repository by lazy {
        RegisterRepositoryImp()
    }

    operator fun invoke(email: String, data: HashMap<String, Any>): Task<Void> {
        return repository.setRegisterInfo(email, data)
    }

}