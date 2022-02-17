package mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.StorageReference
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.data.datasource.MonitorDataSource
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.domain.repository.MonitorRepository


class MonitorRepositoryImp: MonitorRepository {

    private val dataSource by lazy { MonitorDataSource() }

    override fun getUserData(token: String): Task<QuerySnapshot> {
        return dataSource.getFirebaseFromService()
            .collection("users")
            .whereEqualTo("userToken", token).get()
    }

    override fun getTokensFromServer(email: String): Task<DocumentSnapshot> {
        return dataSource.getFirebaseFromService()
            .collection("linkUsers")
            .document(email).get()
    }

    override fun getImageFromStorage(id: String): StorageReference {
        return dataSource.getFirebaseStorageFromService()
            .getReference("image/${id}")
    }

}