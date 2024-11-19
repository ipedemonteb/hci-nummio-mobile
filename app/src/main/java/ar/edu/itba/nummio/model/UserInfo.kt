package ar.edu.itba.nummio.model

//import kotlinx.serialization.SerialName //por si le quiero cambiar el nombre a alguna variable
import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val birthDate: String
)

