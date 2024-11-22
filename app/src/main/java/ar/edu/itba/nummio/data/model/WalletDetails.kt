package ar.edu.itba.nummio.data.model

import ar.edu.itba.nummio.data.network.model.NetworkWalletDetails
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WalletDetails(
    var id: Int,
    var balance: Double,
    var invested: Double,
    var cbu: String,
    var alias: String,
    var createdAt: Date? = null,
    var updatedAt: Date? = null
) {
    fun asNetworkModel(): NetworkWalletDetails {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault(Locale.Category.FORMAT))
        return NetworkWalletDetails(
            id,
            balance,
            invested,
            cbu,
            alias,
            createdAt = createdAt?.let { dateFormat.format(createdAt!!) },
            updatedAt = updatedAt?.let { dateFormat.format(updatedAt!!) }
        )
    }
}