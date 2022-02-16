package mx.edu.tecnologicodecoacalco.babberlink.dashboard.settings.data.services

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.settings.domain.services.SettingsService

class SettingsRemoteService: SettingsService {

    private val firestore by lazy {
        Firebase.firestore
    }

    private val firebaseStorage by lazy {
        FirebaseStorage.getInstance()
    }

    override fun getFireStoreService(): FirebaseFirestore {
        return firestore
    }

    override fun getFirebaseStorageService(): FirebaseStorage {
        return firebaseStorage
    }


}