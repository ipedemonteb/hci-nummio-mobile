package ar.edu.itba.nummio.model

import kotlinx.serialization.Serializable

@Serializable
data class CardData (
    val id : Int,
    val number: String,
    val expirationDate: String,
    val fullName: String,
    val type: String,
    val createdAt: String,
    val updatedAt: String
)