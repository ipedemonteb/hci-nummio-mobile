package ar.edu.itba.nummio.data.network.model

import ar.edu.itba.nummio.data.model.WalletDetails
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Locale

@Serializable
data class NetworkWalletDetails(
    var id: Int,
    var balance: Double,
    var invested: Double,
    var cbu: String,
    var alias: String,
    var createdAt: String?,
    var updatedAt: String?
) {
    fun asModel(): WalletDetails {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault(Locale.Category.FORMAT))
        return WalletDetails(
            id,
            balance,
            invested,
            cbu,
            alias,
            createdAt = createdAt?.let { dateFormat.parse(createdAt!!) },
            updatedAt = updatedAt?.let { dateFormat.parse(updatedAt!!) }
        )
    }
}