package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.repository

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

interface MonitorRepository {
    fun getBabyDataFromFirestore(email: String): Task<QuerySnapshot>
    fun getBabyLpmFromFirestore(email: String): Task<QuerySnapshot>
    fun getBabyLpmWithListener(email: String): CollectionReference
    fun getImageFromStorage(id: String): StorageReference
    fun setUpdateBabyInfo(email: String, id: String, data: HashMap<String, Any>): Task<Void>
    fun setDeleteBabyImage(imageId: String): Task<Void>
    fun setSaveBabyImage(uri: Uri, imageId:String): UploadTask
    fun getUserPhone(email: String): Task<DocumentSnapshot>
}