package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.settings.domain.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

interface SettingsSource {
    fun getFirebaseFromService(): FirebaseFirestore
    fun getFirebaseStorageFromService(): FirebaseStorage
}