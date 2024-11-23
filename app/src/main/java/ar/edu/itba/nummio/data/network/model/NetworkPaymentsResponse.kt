package ar.edu.itba.nummio.data.network.model


import ar.edu.itba.nummio.data.model.CardType
import ar.edu.itba.nummio.data.model.PaymentCard
import ar.edu.itba.nummio.data.model.PaymentData
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPaymentsResponse(
    val payments: List<NetworkPaymentData>
)

@Serializable
data class NetworkPaymentData(
    val id: Int,
    val type: String,
    val amount: Double,
    val balanceBefore: Double,
    val balanceAfter: Double,
    val receiverBalanceBefore: Double?,
    val receiverBalanceAfter: Double?,
    val pending: Boolean,
    val linkUuid: String?,
    val createdAt: String,
    val updatedAt: String,
    val card: NetworkPaymentCard? = null,
    val payer: NetworkPaymentUser? = null,
    val receiver: NetworkPaymentUser? = null
) {
    fun asModel(): PaymentData {
        return PaymentData(
            id = id,
            type = type,
            amount = amount,
            balanceBefore = balanceBefore,
            balanceAfter = balanceAfter,
            receiverBalanceBefore = receiverBalanceBefore,
            receiverBalanceAfter = receiverBalanceAfter,
            pending = pending,
            linkUuid = linkUuid,
            createdAt = createdAt,
            updatedAt = updatedAt,
            card = card?.asModel(),
            payer = payer?.asModel(),
            receiver = receiver?.asModel()
        )
    }
}

@Serializable
data class NetworkPaymentCard(
    val id: Int,
    val number: String,
    val expirationDate: String,
    val fullName: String,
    val type: String
) {
    fun asModel(): PaymentCard? {
        return PaymentCard(
            id = id,
            number = number,
            expirationDate = expirationDate,
            fullName = fullName,
            type = when (type) {
                "DEBIT" -> CardType.DEBIT
                else -> CardType.CREDIT
            }
        )
    }
}

@Serializable
data class NetworkPaymentUser(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val birthDate: String
) {
    fun asModel() = ar.edu.itba.nummio.data.model.PaymentUser(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        birthDate = birthDate
    )
}


/*
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
}*/
