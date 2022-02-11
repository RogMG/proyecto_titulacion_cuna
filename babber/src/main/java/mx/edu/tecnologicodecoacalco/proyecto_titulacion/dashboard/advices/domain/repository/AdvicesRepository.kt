package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.rxjava3.core.Observable
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.domain.model.AdvicesModelDTO

interface AdvicesRepository {
    fun getFireStoreData(): Task<QuerySnapshot>
}