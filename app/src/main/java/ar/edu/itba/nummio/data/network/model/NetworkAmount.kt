package ar.edu.itba.nummio.data.network.model

import ar.edu.itba.nummio.data.model.Amount
import kotlinx.serialization.Serializable

@Serializable
data class NetworkAmount(
    var amount: Double
) {
    fun asModel(): Amount {
        return Amount (
            amount
        )
    }
}