package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

interface MonitorRepository {
    fun getBabyDataFromFirestore(email: String): Task<QuerySnapshot>
    fun getBabyLpmFromFirestore(email: String): Task<QuerySnapshot>
    fun getBabyLpmWithListener(email: String, babyId: String): Task<DocumentSnapshot>
}