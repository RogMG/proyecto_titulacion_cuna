package mx.edu.tecnologicodecoacalco.babberlink.dashboard.settings.data.repository

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.StorageReference
import io.reactivex.rxjava3.core.Observable
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.settings.data.datasource.SettingsDataSource
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.settings.domain.repository.SettingsRepository

import java.util.*
import kotlin.collections.HashMap


class SettingsRepositoryImp: SettingsRepository {

    private val dataSource by lazy { SettingsDataSource() }

    override fun getUserDataFromFirestore(email: String): Task<DocumentSnapshot> {
        return dataSource.getFirebaseFromService()
            .collection("linkUsers")
            .document(email).get()
    }

    override fun getImageFromStorage(id: String): StorageReference {
        return dataSource.getFirebaseStorageFromService()
            .getReference("image/${id}")
    }

    override fun setUserDataUpdate(email: String, data: HashMap<String, Any>): Task<Void> {
        return dataSource.getFirebaseFromService()
            .collection("linkUsers").document(email)
            .update(data)
    }

    override fun setDeleteUserImage(imageId: String): Task<Void> {
        return dataSource.getFirebaseStorageFromService()
            .getReference("image/${imageId}")
            .delete()
    }


}