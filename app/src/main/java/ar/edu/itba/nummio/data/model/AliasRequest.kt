package ar.edu.itba.nummio.data.model

import ar.edu.itba.nummio.data.network.model.NetworkAliasRequest

class AliasRequest(
    val alias: String
) {
    fun asNetworkModel(): NetworkAliasRequest {
        return NetworkAliasRequest(
            alias
        )
    }
}