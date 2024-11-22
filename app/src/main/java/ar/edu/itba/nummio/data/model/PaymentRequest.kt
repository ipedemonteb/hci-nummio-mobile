package ar.edu.itba.nummio.data.model

class PaymentRequest (
    var amount: Int,
    var description: String,
    var type: String,
    var receiverEmail: String? = null,
    var cardId: Int? = null
){
    fun asNetworkModel() = ar.edu.itba.nummio.data.network.model.NetworkPaymentRequest(
        amount = amount,
        description = description,
        type = type,
        receiverEmail = receiverEmail,
        cardId = cardId
    )

}
