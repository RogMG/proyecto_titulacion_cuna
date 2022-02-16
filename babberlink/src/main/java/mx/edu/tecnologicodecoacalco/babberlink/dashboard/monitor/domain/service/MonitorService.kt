package mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.domain.service

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

interface MonitorService {
    fun getFireStoreService(): FirebaseFirestore
    fun getFirebaseStorageService(): FirebaseStorage
}