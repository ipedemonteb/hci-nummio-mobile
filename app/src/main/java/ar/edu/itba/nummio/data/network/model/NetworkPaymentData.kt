package ar.edu.itba.nummio.data.network.model

import ar.edu.itba.nummio.data.model.CardType
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPaymentData(
    val id: Int,
    val type: String,
    val amount: Double,
    val balanceBefore: Double,
    val balanceAfter: Double,
    val pending: Boolean,
    val linkUuid: String?,
    val createdAt: String,
    val updatedAt: String,
    val card: PaymentCard?
){
    fun asModel() = ar.edu.itba.nummio.data.model.PaymentData(
        id = id,
        type = type,
        amount = amount,
        balanceBefore = balanceBefore,
        balanceAfter = balanceAfter,
        pending = pending,
        linkUuid = linkUuid,
        createdAt = createdAt,
        updatedAt = updatedAt,
        card = card?.asModel()
    )
}

@Serializable
data class PaymentCard(
    val id: Int,
    val number: String,
    val expirationDate: String,
    val fullName: String,
    val type: String
){
    fun asModel() = ar.edu.itba.nummio.data.model.PaymentCard(
        id = id,
        number = number,
        expirationDate = expirationDate,
        fullName = fullName,
        type = when (type) { "DEBIT" -> CardType.DEBIT else -> CardType.CREDIT }
    )
}