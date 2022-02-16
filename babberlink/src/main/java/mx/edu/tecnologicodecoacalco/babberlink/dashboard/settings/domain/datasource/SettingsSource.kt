package mx.edu.tecnologicodecoacalco.babberlink.dashboard.settings.domain.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

interface SettingsSource {
    fun getFirebaseFromService(): FirebaseFirestore
    fun getFirebaseStorageFromService(): FirebaseStorage
}