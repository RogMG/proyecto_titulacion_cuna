package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.datasource

import com.google.firebase.firestore.FirebaseFirestore

interface MonitorData {
    fun getFirebaseFromService(): FirebaseFirestore
}