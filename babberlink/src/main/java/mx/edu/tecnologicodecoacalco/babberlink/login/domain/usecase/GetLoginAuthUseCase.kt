package mx.edu.tecnologicodecoacalco.babberlink.login.domain.usecase

import android.app.Activity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import mx.edu.tecnologicodecoacalco.babberlink.login.data.model.LoginModel
import mx.edu.tecnologicodecoacalco.babberlink.login.data.repository.LoginRepositoryImp

class GetLoginAuthUseCase {

    private val repository by lazy {
        LoginRepositoryImp()
    }

    operator fun invoke(email: String, password:String, context: Activity): Task<AuthResult> {
        return repository.getLogin(email, password, context)
    }

}