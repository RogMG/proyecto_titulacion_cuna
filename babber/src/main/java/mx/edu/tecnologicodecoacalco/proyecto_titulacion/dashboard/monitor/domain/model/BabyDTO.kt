package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.model

import com.google.firebase.firestore.PropertyName

data class BabyDTO(
    @PropertyName("nombre")
    val nombre: String = "",
    @PropertyName("apellidoMaterno")
    val apellidoMaterno: String = "",
    @PropertyName("apellidoPaterno")
    val apellidoPaterno: String = "",
    @PropertyName("edad")
    val edad: String = "",
    @PropertyName("monitor")
    val monitor: String = "",
    @PropertyName("peso")
    val peso: String = "",
    @PropertyName("sexo")
    val sexo: String = ""
)

