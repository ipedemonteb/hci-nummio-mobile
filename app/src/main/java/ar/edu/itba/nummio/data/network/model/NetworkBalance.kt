package ar.edu.itba.nummio.data.network.model

import ar.edu.itba.nummio.data.model.Balance
import kotlinx.serialization.Serializable

@Serializable
data class NetworkBalance (
    var balance: Double,
) {
    fun asModel(): Balance {
        return Balance(
            balance
        )
    }
}