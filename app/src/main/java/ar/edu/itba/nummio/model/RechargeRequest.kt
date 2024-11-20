package ar.edu.itba.nummio.model

import kotlinx.serialization.Serializable

@Serializable
data class RechargeRequest (
    val amount : Double
)