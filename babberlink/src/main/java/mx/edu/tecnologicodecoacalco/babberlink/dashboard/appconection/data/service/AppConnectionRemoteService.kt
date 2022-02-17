package mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.data.service

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.domain.service.AppConnectionService

class AppConnectionRemoteService: AppConnectionService {

    private val firestore by lazy {
        Firebase.firestore
    }


    override fun getFireStoreService(): FirebaseFirestore {
        return firestore
    }





}