package mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.StorageReference
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.data.datasource.AppConnectionDataSource
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.domain.repository.AppConnectionRepository

class AppConnectionRepositoryImpl: AppConnectionRepository {

    private val dataSource by lazy{
        AppConnectionDataSource()
    }

    override fun saveTokenInUser(email: String, token: String): Task<Void> {
        return dataSource.getFirebaseFromService()
            .collection("linkUsers")
            .document(email).update("dispositivosVinculados", FieldValue.arrayUnion(token))
    }

    override fun getUserData(token: String): Task<QuerySnapshot> {
        return dataSource.getFirebaseFromService()
            .collection("users")
            .whereEqualTo("userToken", token).get()
    }


}