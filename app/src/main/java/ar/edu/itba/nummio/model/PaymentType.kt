package ar.edu.itba.nummio.model

import kotlinx.serialization.Serializable

@Serializable
data class PaymentType (
    val type : String
)