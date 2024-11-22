package ar.edu.itba.nummio.data.network.model

import ar.edu.itba.nummio.data.model.Investment
import kotlinx.serialization.Serializable

@Serializable
data class NetworkInvestment (
    var investment: Double
) {
    fun asModel(): Investment {
        return Investment (
            investment
        )
    }
}