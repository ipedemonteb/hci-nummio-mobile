package ar.edu.itba.nummio.data.network.model

import ar.edu.itba.nummio.data.model.NewBalance
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class NetworkNewBalance (
    val newBalance : Double = 0.0,
        val linkUuid: String = ""
) {
        fun asModel(): NewBalance {
                return NewBalance(
                        newBalance, linkUuid
                )
        }
}