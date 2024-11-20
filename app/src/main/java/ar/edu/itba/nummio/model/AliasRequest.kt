package ar.edu.itba.nummio.model

import kotlinx.serialization.Serializable

@Serializable
data class AliasRequest(
    val alias: String
)