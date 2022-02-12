package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.settings.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.StorageReference
import io.reactivex.rxjava3.core.Observable
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.domain.model.AdvicesModelDTO

interface SettingsRepository {
    fun getUserDataFromFirestore(email: String): Task<DocumentSnapshot>
    fun getImageFromStorage(id: String): StorageReference
    fun setUserDataUpdate(email: String, data: HashMap<String, Any>): Task<Void>
    fun setDeleteUserImage(imageId: String): Task<Void>
}