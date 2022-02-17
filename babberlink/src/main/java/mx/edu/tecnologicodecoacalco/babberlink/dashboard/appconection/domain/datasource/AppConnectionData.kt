package mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.domain.datasource

import com.google.firebase.firestore.FirebaseFirestore

interface AppConnectionData {
    fun getFirebaseFromService(): FirebaseFirestore
}