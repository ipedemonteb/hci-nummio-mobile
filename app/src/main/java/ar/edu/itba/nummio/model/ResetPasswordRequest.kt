package ar.edu.itba.nummio.model
import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordRequest(
    val token: String,
    val password: String
)
