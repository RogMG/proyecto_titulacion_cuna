package mx.edu.tecnologicodecoacalco.babberlink.login.domain.usecase

import android.app.Activity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import mx.edu.tecnologicodecoacalco.babberlink.login.data.repository.RegisterRepositoryImp

class SetRegisterAuthUseCase {

    private val repository by lazy {
        RegisterRepositoryImp()
    }

    operator fun invoke(
        email: String,
        password: String
    ): Task<AuthResult> {
        return repository.setRegisterAuth(
           password = password,
           email = email
        )

    }

}