package ar.edu.itba.nummio.data.model

import ar.edu.itba.nummio.data.network.model.NetworkInvestment

class Investment (
    var investment: Double
) {
    fun asNetworkModel(): NetworkInvestment {
        return NetworkInvestment(
            investment
        )
    }
}