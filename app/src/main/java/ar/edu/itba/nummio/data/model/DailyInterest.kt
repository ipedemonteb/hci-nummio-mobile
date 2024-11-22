package ar.edu.itba.nummio.data.model

import ar.edu.itba.nummio.data.network.model.NetworkDailyInterest

class DailyInterest(
    val interest: Double
) {
    fun asNetworkModel(): NetworkDailyInterest {
        return NetworkDailyInterest(
            interest
        )
    }
}