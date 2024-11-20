import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@JsonClassDiscriminator("responseType")
@Serializable
sealed class PaymentResponse

@Serializable
@SerialName("linkUuid")
data class LinkUuidResponse(
    val linkUuid: String
) : PaymentResponse()

@Serializable
@SerialName("message")
data class MessageResponse(
    val message: String
) : PaymentResponse()

@Serializable
@SerialName("newBalance")
data class NewBalanceResponse(
    val newBalance: Double
) : PaymentResponse()
