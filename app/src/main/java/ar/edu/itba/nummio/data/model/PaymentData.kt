package ar.edu.itba.nummio.data.model

class PaymentData(
    var id: Int,
    var type: String,
    var amount: Double,
    var balanceBefore: Double,
    var balanceAfter: Double,
    var pending: Boolean,
    var linkUuid: String?,
    var createdAt: String,
    var updatedAt: String,
    var card: PaymentCard?
){
    fun asNetworkModel() = ar.edu.itba.nummio.data.network.model.NetworkPaymentData(
        id = id,
        type = type,
        amount = amount,
        balanceBefore = balanceBefore,
        balanceAfter = balanceAfter,
        pending = pending,
        linkUuid = linkUuid,
        createdAt = createdAt,
        updatedAt = updatedAt,
        card = card?.asNetworkModel()
    )
}

class PaymentCard(
    var id: Int,
    var number: String,
    var expirationDate: String,
    var fullName: String,
    var type: CardType
){
    fun asNetworkModel() = ar.edu.itba.nummio.data.network.model.PaymentCard(
        id = id,
        number = number,
        expirationDate = expirationDate,
        fullName = fullName,
        type = type.toString()
    )
}