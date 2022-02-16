package mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.data.repository

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.data.datasource.MonitorDataSource
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.domain.repository.MonitorRepository


class MonitorRepositoryImp: MonitorRepository {

    private val dataSource by lazy { MonitorDataSource() }

    override fun getBabyDataFromFirestore(email: String): Task<QuerySnapshot> {
        val db = dataSource.getFirebaseFromService()
        return db.collection("linkUsers")
            .document(email)
            .collection("babies").get()
    }

    override fun getBabyLpmFromFirestore(email: String): Task<QuerySnapshot> {
        val db = dataSource.getFirebaseFromService()
        return db.collection("users")
            .document(email)
            .collection("babies")
            .get()
    }

    override fun getBabyLpmWithListener(email: String):CollectionReference {
        val db = dataSource.getFirebaseFromService()
        return db.collection("linkUsers")
            .document(email)
            .collection("babies")

    }


    override fun getImageFromStorage(id: String): StorageReference {
        return dataSource.getFirebaseStorageFromService()
            .getReference("image/${id}")
    }





}