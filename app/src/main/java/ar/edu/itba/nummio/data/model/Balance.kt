package ar.edu.itba.nummio.data.model

import ar.edu.itba.nummio.data.network.model.NetworkBalance

class Balance (
    val balance: Double
) {
    fun asNetworkModel(): NetworkBalance {
        return NetworkBalance(
            balance
        )
    }
}