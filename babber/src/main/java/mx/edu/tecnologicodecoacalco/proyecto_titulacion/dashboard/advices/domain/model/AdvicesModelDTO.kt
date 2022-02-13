package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.domain.model

import com.google.firebase.firestore.PropertyName
import java.io.Serializable

data class AdvicesModelDTO(
    @PropertyName("content")
    val content: String = "",
    @PropertyName("description")
    val description: String = "",
    @PropertyName("linkArticle")
    val linkArticle: String = "",
    @PropertyName("linkImage")
    val linkImage: String = "",
    @PropertyName("title")
    val title: String = ""
): Serializable

