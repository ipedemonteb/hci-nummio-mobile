package ar.edu.itba.nummio.data.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.data.network.model.NetworkPaymentCard
import ar.edu.itba.nummio.data.network.model.NetworkPaymentData
import ar.edu.itba.nummio.data.network.model.NetworkPaymentUser
import ar.edu.itba.nummio.ui.home.TransactionData
import kotlinx.serialization.Serializable

class PaymentData(
    var id: Int,
    var type: String,
    var amount: Double,
    var balanceBefore: Double,
    var balanceAfter: Double,
    var receiverBalanceBefore: Double?,
    var receiverBalanceAfter: Double?,
    var pending: Boolean,
    var linkUuid: String?,
    var createdAt: String,
    var updatedAt: String,
    val payer: PaymentUser?,
    val receiver: PaymentUser?,
    var card: PaymentCard?
) {
    fun asNetworkModel() = NetworkPaymentData(
        id = id,
        type = type,
        amount = amount,
        balanceBefore = balanceBefore,
        balanceAfter = balanceAfter,
        pending = pending,
        linkUuid = linkUuid,
        createdAt = createdAt,
        updatedAt = updatedAt,
        card = card?.asNetworkModel(),
        receiverBalanceBefore = receiverBalanceBefore,
        receiverBalanceAfter = receiverBalanceAfter,
        payer = payer!!.asNetworkModel(),
        receiver = receiver!!.asNetworkModel()
    )
    @Composable
    fun asTransactionData(email:String) =
        TransactionData(
            message = if (email != receiver?.email) stringResource(R.string.sent_payment) else stringResource(R.string.received_payment),
            amount = amount,
            date = createdAt,
            destination = ((receiver?.firstName + " "+ receiver?.lastName) ?: "")
    )

}

class PaymentCard(
    var id: Int,
    var number: String,
    var expirationDate: String,
    var fullName: String,
    var type: CardType
) {
    fun asNetworkModel() = NetworkPaymentCard(
        id = id,
        number = number,
        expirationDate = expirationDate,
        fullName = fullName,
        type = type.toString()
    )
}


class PaymentUser(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val birthDate: String
) {
    fun asNetworkModel() = NetworkPaymentUser(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        birthDate = birthDate
    )
}