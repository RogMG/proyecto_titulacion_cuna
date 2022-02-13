package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.model

import com.google.firebase.firestore.PropertyName
import java.io.Serializable

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
    var monitor: String = "",
    @PropertyName("imageId")
    var imageId: String = "",
    @PropertyName("peso")
    val peso: String = "",
    @PropertyName("sexo")
    val sexo: String = ""
): Serializable

