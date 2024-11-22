import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@JsonClassDiscriminator("type")
@Serializable
sealed class NetworkPaymentRequest {
    abstract val amount: Int
    abstract val description: String
}

@Serializable
@SerialName("BALANCE")
data class NetworkBalancePaymentRequest(
    override val amount: Int,
    override val description: String,
    val receiverEmail: String
) : NetworkPaymentRequest()

@Serializable
@SerialName("CARD")
data class NetworkCardPaymentRequest(
    override val amount: Int,
    override val description: String,
    val cardId: Int,
    val receiverEmail: String
) : NetworkPaymentRequest()

@Serializable
@SerialName("LINK")
data class NetworkLinkPaymentRequest(
    override val amount: Int,
    override val description: String
) : NetworkPaymentRequest()
