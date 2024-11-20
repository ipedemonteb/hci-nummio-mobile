package ar.edu.itba.nummio.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SuccessAndMessage(
    val success: String,
    val message: String = ""
)