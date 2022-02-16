package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.appconection.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot

interface AppConnectionRepository {

    fun getUserToken(email: String): Task<DocumentSnapshot>

}