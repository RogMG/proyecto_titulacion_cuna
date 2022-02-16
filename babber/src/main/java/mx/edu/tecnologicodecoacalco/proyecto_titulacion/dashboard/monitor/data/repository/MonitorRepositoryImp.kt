package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.data.repository

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.data.datasource.AdvicesDataSource
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.data.datasource.MonitorDataSource
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.repository.MonitorRepository

class MonitorRepositoryImp: MonitorRepository {

    private val dataSource by lazy { MonitorDataSource() }

    override fun getBabyDataFromFirestore(email: String): Task<QuerySnapshot> {
        val db = dataSource.getFirebaseFromService()
        return db.collection("users")
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
        return db.collection("users")
            .document(email)
            .collection("babies")

    }

    override fun setUpdateBabyInfo(email: String, id: String, data: HashMap<String, Any>): Task<Void>{
        return dataSource.getFirebaseFromService()
            .collection("users").document(email)
            .collection("babies").document(id)
            .update(data)
    }

    override fun setDeleteBabyImage(imageId: String): Task<Void> {
        return dataSource.getFirebaseStorageFromService()
            .getReference("image/${imageId}")
            .delete()
    }

    override fun setSaveBabyImage(uri: Uri, imageId: String): UploadTask {
        return dataSource.getFirebaseStorageFromService()
            .getReference("image/${imageId}")
            .putFile(uri)
    }

    override fun getImageFromStorage(id: String): StorageReference {
        return dataSource.getFirebaseStorageFromService()
            .getReference("image/${id}")
    }

    override fun getUserPhone(email: String): Task<DocumentSnapshot> {
        return dataSource.getFirebaseFromService()
            .collection("users").document(email)
            .get()
    }





}