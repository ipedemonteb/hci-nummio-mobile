package ar.edu.itba.nummio.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkUserRequest(
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val email: String,
    val password: String
)