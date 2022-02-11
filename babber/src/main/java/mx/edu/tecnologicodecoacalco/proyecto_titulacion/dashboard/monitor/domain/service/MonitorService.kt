package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.service

import com.google.firebase.firestore.FirebaseFirestore

interface MonitorService {
    fun getFireStoreService(): FirebaseFirestore
}