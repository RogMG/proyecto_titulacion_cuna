package mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.domain.model

import android.net.Uri
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.domain.model.UserModelDTO

data class UserDataCompiled(
    val nombre: String = "" ,
    val celular: String = "",
    val token: String = "",
    val image: Uri = Uri.EMPTY
)
