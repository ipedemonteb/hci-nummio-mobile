package ar.edu.itba.nummio.data.model

import ar.edu.itba.nummio.data.network.model.NetworkNewBalance

class NewBalance(
    var newBalance: Double
) {
    fun asNetworkModel(): NetworkNewBalance {
        return NetworkNewBalance(
            newBalance
        )
    }
}