package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

interface MonitorData {
    fun getFirebaseFromService(): FirebaseFirestore
    fun getFirebaseStorageFromService(): FirebaseStorage
}