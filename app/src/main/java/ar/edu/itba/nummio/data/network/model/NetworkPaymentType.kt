package ar.edu.itba.nummio.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkPaymentType (
    val type : String
)

@Serializable
data class NetworkPaymentTypeCard (
    val type : String,
    val cardId: Int
)