package ar.edu.itba.nummio.data.model

import ar.edu.itba.nummio.data.network.model.NetworkAmount

class Amount(
    var amount: Double
) {
    fun asNetworkModel(): NetworkAmount {
        return NetworkAmount (
            amount
        )
    }
}