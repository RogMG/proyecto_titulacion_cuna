package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.data.repository

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.rxjava3.core.Observable
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.data.datasource.AdvicesDataSource
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.domain.model.AdvicesModelDTO
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.domain.repository.AdvicesRepository
import java.util.*


class AdvicesRepositoryImp: AdvicesRepository {

    private val dataSource by lazy { AdvicesDataSource() }

        override fun getFireStoreData(): Task<QuerySnapshot> {
            val db = dataSource.getFirebaseFromService()
            return db.collection("advices").get()
        }




}