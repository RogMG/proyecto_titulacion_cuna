package mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.domain.usecase

import android.app.Activity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.repository.RegisterRepositoryImp

class SetRegisterAuthUseCase {

    private val repository by lazy {
        RegisterRepositoryImp()
    }

    operator fun invoke(
        name: String,
        dadLastName: String,
        momLastName: String,
        password: String,
        phone: String,
        email: String,
        context: Activity
    ): Task<AuthResult> {
        return repository.setRegisterAuth(
           name = name,
           dadLastName = dadLastName,
           momLastName = momLastName,
           password = password,
           phone = phone,
           email = email,
           context = context
        )

    }

}