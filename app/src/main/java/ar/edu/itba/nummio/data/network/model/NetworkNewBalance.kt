package ar.edu.itba.nummio.data.network.model

import ar.edu.itba.nummio.data.model.NewBalance

class NetworkNewBalance(
    var newBalance: Double
) {
    fun asModel(): NewBalance {
        return NewBalance(
            newBalance
        )
    }
}