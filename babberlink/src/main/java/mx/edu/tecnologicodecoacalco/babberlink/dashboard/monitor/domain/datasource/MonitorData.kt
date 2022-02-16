package mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.domain.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

interface MonitorData {
    fun getFirebaseFromService(): FirebaseFirestore
    fun getFirebaseStorageFromService(): FirebaseStorage
}