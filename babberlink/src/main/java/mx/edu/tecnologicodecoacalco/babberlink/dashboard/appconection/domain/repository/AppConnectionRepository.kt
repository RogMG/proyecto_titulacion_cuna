package mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.StorageReference

interface AppConnectionRepository {
    fun saveTokenInUser(email: String, token: String): Task<Void>
    fun getUserData(token: String): Task<QuerySnapshot>
}