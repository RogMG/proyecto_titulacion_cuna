package mx.edu.tecnologicodecoacalco.babberlink.dashboard.settings.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.storage.StorageReference

interface SettingsRepository {
    fun getUserDataFromFirestore(email: String): Task<DocumentSnapshot>
    fun getImageFromStorage(id: String): StorageReference
    fun setUserDataUpdate(email: String, data: HashMap<String, Any>): Task<Void>
    fun setDeleteUserImage(imageId: String): Task<Void>
}