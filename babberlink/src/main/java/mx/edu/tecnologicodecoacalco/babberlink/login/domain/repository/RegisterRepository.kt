package mx.edu.tecnologicodecoacalco.babberlink.login.domain.repository

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import mx.edu.tecnologicodecoacalco.babberlink.login.data.model.RegisterModel
import java.io.Serializable

interface RegisterRepository {
    fun setRegisterAuth(password: String, email: String): Task<AuthResult>

    fun setRegisterInfo(email: String, data: HashMap<String, Any>): Task<Void>
}