package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.settings.domain.model

import com.google.firebase.firestore.PropertyName

data class UserDataModel (
    @PropertyName("apellidoMaterno")
    val apellidoMaterno: String = "",
    @PropertyName("apellidoPaterno")
    val apellidoPaterno: String = "",
    @PropertyName("celular")
    val celular: String = "",
    @PropertyName("correo")
    val correo: String = "",
    @PropertyName("userToken")
    val userToken: String = "",
    @PropertyName("imageId")
    val imageId: String = "",
    @PropertyName("nombre")
    val nombre: String = ""
)
