package ar.edu.itba.nummio.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkPaymentRequest (
    val amount: Int,
    val description: String,
    val type: String,
    val receiverEmail: String? = null,
    val cardId: Int? = null
    ){
    fun asNetworkModel() = ar.edu.itba.nummio.data.model.PaymentRequest(
        amount = amount,
        description = description,
        type = type,
        receiverEmail = receiverEmail,
        cardId = cardId
    )
}
