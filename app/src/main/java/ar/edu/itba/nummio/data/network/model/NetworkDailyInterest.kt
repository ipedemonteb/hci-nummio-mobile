package ar.edu.itba.nummio.data.network.model

import kotlinx.serialization.Serializable
import ar.edu.itba.nummio.data.model.DailyInterest

@Serializable
data class NetworkDailyInterest (
    val interest : Double
) {
    fun asModel(): DailyInterest {
        return DailyInterest(
            interest
        )
    }
}