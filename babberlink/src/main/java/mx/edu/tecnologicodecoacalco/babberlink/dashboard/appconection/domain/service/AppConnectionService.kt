package mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.domain.service

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

interface AppConnectionService {
    fun getFireStoreService(): FirebaseFirestore
}