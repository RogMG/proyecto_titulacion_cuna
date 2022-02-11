package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.data.datasource.AdvicesDataSource
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.data.datasource.MonitorDataSource
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.repository.MonitorRepository

class MonitorRepositoryImp: MonitorRepository {

    private val dataSource by lazy { MonitorDataSource() }

    override fun getBabyDataFromFirestore(email: String): Task<QuerySnapshot> {
        val db = dataSource.getFirebaseFromService()
        return db.collection("users")
            .document(email)
            .collection("babies").get()
    }

    override fun getBabyLpmFromFirestore(email: String): Task<QuerySnapshot> {
        val db = dataSource.getFirebaseFromService()
        return db.collection("users")
            .document(email)
            .collection("babies")
            .get()
    }

    override fun getBabyLpmWithListener(email: String, babyId: String): Task<DocumentSnapshot> {
        val db = dataSource.getFirebaseFromService()
        return db.collection("users")
            .document(email)
            .collection("babies")
            .document(babyId).get()
    }


}