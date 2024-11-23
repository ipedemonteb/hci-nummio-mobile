package ar.edu.itba.nummio.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkPaymentResponse(
    val payment: NetworkPaymentData
)