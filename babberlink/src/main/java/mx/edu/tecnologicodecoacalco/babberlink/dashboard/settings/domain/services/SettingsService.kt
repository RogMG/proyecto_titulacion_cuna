package mx.edu.tecnologicodecoacalco.babberlink.dashboard.settings.domain.services

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

interface SettingsService {
    fun getFireStoreService(): FirebaseFirestore
    fun getFirebaseStorageService(): FirebaseStorage
}