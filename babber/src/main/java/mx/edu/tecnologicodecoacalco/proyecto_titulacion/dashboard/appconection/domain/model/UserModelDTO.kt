package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.appconection.domain.model

import com.google.firebase.firestore.PropertyName

data class UserModelDTO (
    @PropertyName("apellidoMaternov")
    val  apellidoMaterno :String =  "",
    @PropertyName("apellidoPaternov")
    val  apellidoPaterno :String =  "",
    @PropertyName("celular")
    val  celular :String =  "",
    @PropertyName("correo")
    val  correo :String =  "",
    @PropertyName("imageId")
    val  imageId:String =  "",
    @PropertyName("nombrev")
    val  nombre :String =  "",
    @PropertyName("userToken")
    val  userToken :String =  "",
)