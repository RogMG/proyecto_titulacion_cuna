package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.babyregister.data.repository

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.rxjava3.core.Observable
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.data.datasource.AdvicesDataSource
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.domain.model.AdvicesModelDTO
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.domain.repository.AdvicesRepository
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.babyregister.data.datasource.BabyRegisterDataSource
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.babyregister.domain.repository.BabyRegisterRepository
import java.util.*
import kotlin.collections.HashMap


class BabyRegisterRepositoryImp: BabyRegisterRepository {

    private val dataSource by lazy { BabyRegisterDataSource() }

        override fun registerBabyInFireStore(email: String, data: HashMap<String, String>): Task<DocumentReference> {
            val db = dataSource.getFirebaseFromService()
            return db.collection("users")
                .document(email)
                .collection("babies")
                .add(data)

        }


}