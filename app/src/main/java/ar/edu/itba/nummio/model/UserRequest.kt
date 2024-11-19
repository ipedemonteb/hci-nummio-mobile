package ar.edu.itba.nummio.model

import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val email: String,
    val password: String
)