package ar.edu.itba.nummio.model

import kotlinx.serialization.Serializable

@Serializable
data class CardRequest (
    val fullName: String,
    val cvv: Int,
    val number: String,
    val expirationDate: String,
    val type: String
)