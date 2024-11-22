package ar.edu.itba.nummio.data.network.model

import ar.edu.itba.nummio.data.model.NewBalance
import kotlinx.serialization.Serializable

@Serializable
data class NetworkNewBalance (
    val newBalance : Double
) {
        fun asModel(): NewBalance {
                return NewBalance(
                        newBalance
                )
        }
}