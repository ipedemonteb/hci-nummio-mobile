import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@JsonClassDiscriminator("type")
@Serializable
sealed class PaymentRequest {
    abstract val amount: Int
    abstract val description: String
}

@Serializable
@SerialName("BALANCE")
data class BalancePaymentRequest(
    override val amount: Int,
    override val description: String,
    val receiverEmail: String
) : PaymentRequest()

@Serializable
@SerialName("CARD")
data class CardPaymentRequest(
    override val amount: Int,
    override val description: String,
    val cardId: Int,
    val receiverEmail: String
) : PaymentRequest()

@Serializable
@SerialName("LINK")
data class LinkPaymentRequest(
    override val amount: Int,
    override val description: String
) : PaymentRequest()
