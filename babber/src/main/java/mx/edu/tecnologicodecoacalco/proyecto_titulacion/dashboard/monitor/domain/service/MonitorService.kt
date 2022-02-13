package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.service

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

interface MonitorService {
    fun getFireStoreService(): FirebaseFirestore
    fun getFirebaseStorageService(): FirebaseStorage
}