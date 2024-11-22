package ar.edu.itba.nummio.data.network.model

import ar.edu.itba.nummio.data.model.AliasRequest
import kotlinx.serialization.Serializable

@Serializable
data class NetworkAliasRequest(
    val alias: String
) {
    fun asModel(): AliasRequest {
        return AliasRequest(
            alias
        )
    }
}