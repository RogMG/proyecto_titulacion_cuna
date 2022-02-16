package mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.domain.repository

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
}