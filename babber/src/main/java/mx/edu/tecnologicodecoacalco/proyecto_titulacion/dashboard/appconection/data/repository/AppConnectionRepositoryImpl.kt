package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.appconection.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.appconection.data.datasource.AppConnectionDataSource
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.appconection.domain.repository.AppConnectionRepository

class AppConnectionRepositoryImpl: AppConnectionRepository {

    private val dataSource by lazy{
        AppConnectionDataSource()
    }

    override fun getUserToken(email: String): Task<DocumentSnapshot> {
       return dataSource.getFirebaseFromService()
            .collection("users")
            .document(email).get()
    }


}