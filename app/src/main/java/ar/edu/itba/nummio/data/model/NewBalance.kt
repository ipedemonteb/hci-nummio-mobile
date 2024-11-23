package ar.edu.itba.nummio.data.model

import ar.edu.itba.nummio.data.network.model.NetworkNewBalance

class NewBalance(
    var newBalance: Double = 0.0,
    var linkUuid: String = ""
) {
    fun asNetworkModel(): NetworkNewBalance {
        return NetworkNewBalance(
            newBalance, linkUuid
        )
    }
}