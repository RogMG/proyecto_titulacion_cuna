package mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.repository

import android.app.Activity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentReference
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.datasource.RegisterDataSourceImp
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.domain.repository.RegisterRepository
import java.io.Serializable

class RegisterRepositoryImp: RegisterRepository {

    private val dataSource by lazy { RegisterDataSourceImp() }

    override fun setRegisterAuth(password: String, email: String): Task<AuthResult> {
        val auth = dataSource.setRegisterFromService()
        return auth.createUserWithEmailAndPassword(email, password)
    }

    override fun setRegisterInfo(email: String, data: HashMap<String, Any>): Task<Void> {
       return dataSource.registerUserToFirestore()
            .collection("users")
            .document(email)
            .set(data)
    }


}