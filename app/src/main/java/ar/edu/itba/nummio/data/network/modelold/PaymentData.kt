package ar.edu.itba.nummio.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class PaymentData(
    val id: Int,
    val type: String,
    val amount: Double,
    val balanceBefore: Double,
    val balanceAfter: Double,
    val pending: Boolean,
    val linkUuid: String?,
    val createdAt: String,
    val updatedAt: String,
    val card: ar.edu.itba.nummio.data.network.model.Card?
)

@Serializable
data class Card(
    val id: Int,
    val number: String,
    val expirationDate: String,
    val fullName: String,
    val type: String
)